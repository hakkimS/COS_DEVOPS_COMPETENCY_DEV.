import groovy.json.JsonSlurper

def jsonSlurper = new JsonSlurper()
def curl_url = "-H \"Accept: application/vnd.github.v3+json\" -H \"Authorization:token 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\""
def response = "curl ${curl_url} https://git.elektrobitautomotive.com/api/v3/repos/EB-EST-COS-A-4/COS_DEVOPS_SANDBOX/pulls".execute().getText()
def data = jsonSlurper.parseText(response)
for(loop in data){
    pulls = loop.url
    pulls_data = "curl ${curl_url} ${pulls}".execute().getText()
    merge_Json = jsonSlurper.parseText(pulls_data)
    if (merge_Json.mergeable_state == "clean"){
        println "Commit message: " + merge_Json.title + " is mergeable"
        merge_url = merge_Json.url + "/merge"
        "curl -X PUT -H \"Accept: application/vnd.github.v3+json\" -H \"Authorization:token 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\"" + merge_url.execute()

    }
    else{
        println "Commit message: " + merge_Json.title + " cannot be merge into master and is " + merge_Json.mergeable_state
    }
}
