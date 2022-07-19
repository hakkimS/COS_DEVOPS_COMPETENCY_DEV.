import groovy.json.JsonSlurper

//global variables
response = ""
jsonSlurper = new JsonSlurper()
jsonData = null
pull_details = null
curl_body = "-H \"Accept: application/vnd.github.v3+json\" " +
        "-H \"Authorization:token bfeee3eb620521d281d4a7dd05ebe38394c30b57 \""

pullurl= "https://git.elektrobitautomotive.com/api/v3/repos/EB-EST-COS-A-4/COS_DEVOPS_SANDBOX/pulls"
println(pullurl)
def urlresponse(url) {
response = "curl ${curl_body} ${url}".execute().getText()
jsonData = jsonSlurper.parseText(response)

//println(response)
return jsonData
}

jsonData=urlresponse(pullurl)
jsonData.each {
    it -> println(it.url)
    pullrequest = urlresponse(it.url)
    if (pullrequest.mergeable_state == "clean") {
        println("source branch ${pullrequest.head.ref} -> target branch ${pullrequest.base.ref} is waiting for merge")
    } else {
        println("source branch ${pullrequest.head.ref} -> target branch ${pullrequest.base.ref} is waiting for review ")

    }

}
