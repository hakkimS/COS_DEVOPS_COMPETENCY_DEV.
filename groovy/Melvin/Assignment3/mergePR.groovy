import groovy.json.JsonSlurper

def jsonSlurper = new JsonSlurper()
conf = jsonSlurper.parse(new File('pr_config.json'))
String all_pr_cmd = "curl -s -u " + conf.id +":"+ conf.pass +" "+ conf.url + conf.org +"/"+conf.repo+"/pulls?state=open"
all_pr_response = all_pr_cmd.execute().text
jsonSlurper.parseText(all_pr_response).each { pr->
    String single_pr_cmd = "curl -s -u " + conf.id +":"+ conf.pass +" "+ pr.url
    single_pr_response = single_pr_cmd.execute().text
    state = jsonSlurper.parseText(single_pr_response).mergeable_state
    if(state == "clean"){
        println("PR number : "+pr.number + " is in "+ state + " state and is going to be merged")
        close_pr_cmd = "curl -s -X PUT -u " + conf.id +":"+ conf.pass +" "+ pr.url +"/merge -d {'commit_title':'Auto Merge'}"
        println(close_pr_cmd)
        close_pr_response = close_pr_cmd.execute().text
        print(close_pr_response)
    }
    else
        println("PR number : "+pr.number + " is in "+ state + " state and cannot be merged")
}
