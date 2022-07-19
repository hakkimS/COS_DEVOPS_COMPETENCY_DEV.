import groovy.json.JsonSlurper

def getMergablePr(file){
    def jsonSlurper = new JsonSlurper()
    def data = jsonSlurper.parse(file)
    def username = data["username"]
    def passtoken = data["passtoken"]
    def response = "curl -s -u ${username}:${passtoken} https://git.elektrobitautomotive.com/api/v3/repos/EB-EST-COS-A-4/COS_DEVOPS_SANDBOX/pulls?state=open".execute()
    response_json = new JsonSlurper().parseText(response.text)
    response_json.each { it ->
        pulls_url = it["url"]
        pulls_url_content = "curl -s -u ${username}:${passtoken} ${pulls_url}".execute().text
        pulls_url_json = new JsonSlurper().parseText(pulls_url_content)
        if (pulls_url_json.mergeable_state == "clean") {
            println pulls_url_json.id + " is mergeable"
            merge_url = pulls_url_json.url + "/merge"
            "curl -s -u ${username}:${passtoken} ${merge_url}".execute()
        }
        else {
            println "for id:" + pulls_url_json.id + " mergeable_state is " + pulls_url_json.mergeable_state
        }
    }
}
def filename = "config.json"
def file = new File(filename)
if (!file.exists()){
    println "config.json not found"
}
else{
    getMergablePr(file)
}
