import groovy.json.JsonSlurper

def jsonSlurper = new JsonSlurper()

def response = "curl -H \"Accept: application/vnd.github.v3+json\" -H \"Authorization:token 4c256a5d513d6ee6180e0c35666dbf78979efc2f\" https://git.elektrobitautomotive.com/api/v3/repos/EB-EST-COS-A-4/COS_DEVOPS_SANDBOX/pulls".execute().text
def data = jsonSlurper.parseText(response)
 data.each { it ->
     pulls = it.url
      pulls_data = "curl -H \"Accept: application/vnd.github.v3+json\" -H \"Authorization:token 4c256a5d513d6ee6180e0c35666dbf78979efc2f\" ${pulls}".execute().text
     merge = jsonSlurper.parseText(pulls_data)
     if (merge.mergeable_state == "clean"){
         println merge.id + " is mergeable"
         megeable_url = merge.url + "/merge"
         "curl -X PUT -H \"Accept: application/vnd.github.v3+json\" -H \"Authorization:token 4c256a5d513d6ee6180e0c35666dbf78979efc2f\" ${megeable_url}".execute()

     }
     else{
         println "for id:" + merge.id + " mergeable_state is " + merge.mergeable_state
     }

}
//print url
