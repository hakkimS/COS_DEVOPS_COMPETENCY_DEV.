import groovy.json.JsonSlurper
def jsonSlurper = new JsonSlurper()
def temp = ["curl", "-H", "'Accept: application/vnd.github.v3+json'", "-u", "sass273491:600b8c1ec63b79867199e25e799ea88d642959f7", "https://git.elektrobitautomotive.com/api/v3/repos/EB-EST-COS-A-4/COS_DEVOPS_SANDBOX/pulls"].execute().text
print temp
print "******************************EOL****************************************"
//groovy map
def data = jsonSlurper.parseText(temp)
data.each { it ->
    pulls = it.url
    pulls_data = ["curl", "-H", "'Accept: application/vnd.github.v3+json'", "-u", "sass273491:600b8c1ec63b79867199e25e799ea88d642959f7", "${pulls}"].execute().text
   print pulls_data
   print "******************************EOL****************************************"
merge = jsonSlurper.parseText(pulls_data)
    if (merge.mergeable_state == "clean") {
        println merge.id + " is mergeable"
        mergeable_url = merge.url + "/merge"
        ["curl", "-X", "PUT", "-H", "'Accept: application/vnd.github.v3+json'", "-u", "sass273491:600b8c1ec63b79867199e25e799ea88d642959f7", "${mergeable_url}"].execute()

    }
    else {
        println "for id:" + merge.id + " mergeable_state is " + merge.mergeable_state
    }
}


