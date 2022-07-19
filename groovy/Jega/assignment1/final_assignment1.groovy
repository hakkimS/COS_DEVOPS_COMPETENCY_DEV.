
import groovy.json.JsonSlurper
def slurper = new JsonSlurper()
def json = new File('input.json')
def contentText = json.text
def jsonFile = slurper.parseText(contentText)
def csvFile = new File('output.csv')
def keys = jsonFile.keySet()
flag = 1;
def write(columns,csvFile) {
            csvFile.append('avdxp_cict_status,')
            columns = columns.toString().replaceAll("\\[","").replaceAll("]", "")
            csvFile.append(columns + "\n")
}

keys.each { key ->
        if (key != '') {
        for (i = 0; i < jsonFile.get(key).size(); i++) {
            def mykey = jsonFile.get(key)[i].toSpreadMap().keySet()
            def myvalue = jsonFile.get(key)[i].toSpreadMap().values()         
            if (flag == 1) {
            write(mykey,csvFile)
            flag = 0;
            }
            write(myvalue,csvFile)
        }
        }
}


