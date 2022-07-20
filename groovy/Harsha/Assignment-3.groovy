import groovy.json.JsonSlurper

def jsonSlurper = new JsonSlurper()
def curl_url = "-H \"Accept: application/vnd.github.v3+json\" -H \"Authorization:token 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\""
def response = "curl ${curl_url} https://git.elektrobitautomotive.com/api/v3/repos/EB-EST-COS-A-4/COS_DEVOPS_SANDBOX/pulls".execute().getText()
def data = jsonSlurper.parseText(response)
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
