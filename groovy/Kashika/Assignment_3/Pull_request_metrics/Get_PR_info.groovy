import groovy.json.JsonSlurper

//global variables
response = ""
jsonSlurper = new JsonSlurper()
jsonData = null
pull_details = null
curl_body = "-H \"Accept: application/vnd.github.v3+json\" " +
        "-H \"Authorization:token 2c1f0077526f82ba8ce8c43b62c4fe96b9cd713a\""

// method for change data into json readable
def get_json(response) {

    jsonData = jsonSlurper.parseText(response)
    return jsonData
}

//Execution of github api url got PR
response = """curl ${curl_body} https://git.elektrobitautomotive.com/api/v3/repos/EB-EST-COS-A-4/COS_DEVOPS_SANDBOX/pulls""".execute().getText()

//get json data
jsonData = get_json(response)

//itration of json data
jsonData.each {
    response = """curl ${curl_body} ${it.url}""".execute().getText()

    //get all pull request url
    pull_details = get_json(response)

    //check if PR is mergable or not
    if (pull_details.mergeable_state == 'clean') {

        println "Pull request status for changes ${pull_details.url} from ${pull_details.user.login} is clean and can "
        +"be merge into master"

        response = """curl -X PUT ${curl_body}  ${pull_details.url}/merge""".execute().getText()
        println(response)

    } else {

        println "Pull request status for changes ${pull_details.url} from ${pull_details.user.login} " + "is ${pull_details.mergeable_state} and can't be merge into master"

    }


}





