import groovy.json.JsonSlurper

def jsonSlurper = new JsonSlurper()
print "===Initial Data==="
def temp = ["curl", "-H", "'Accept: application/vnd.github.v3+json'", "-u", "vila274183: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", "https://git.elektrobitautomotive.com/api/v3/repos/EB-EST-COS-A-4/COS_DEVOPS_SANDBOX/pulls"].execute().text
print temp

def data = jsonSlurper.parseText(temp)
data.each { it ->
    pulls = it.url
    print "===Pulled Data==="
    pulls_data = ["curl", "-H", "'Accept: application/vnd.github.v3+json'", "-u", "vila274183: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", "${pulls}"].execute().text
    print pulls_data
    merge = jsonSlurper.parseText(pulls_data)
    if (merge.mergeable_state == "clean") {
        println merge.id + " is mergeable"
        mergeable_url = merge.url + "/merge"
        ["curl", "-X", "PUT", "-H", "'Accept: application/vnd.github.v3+json'", "-u", "vila274183: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", "${mergeable_url}"].execute()

    }
    else {
        println "for id:" + merge.id + " mergeable_state is " + merge.mergeable_state
    }
}
