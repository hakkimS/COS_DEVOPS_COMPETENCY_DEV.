import groovy.json.JsonSlurper
import groovy.json.JsonOutput

// Parse JSON file and get the json content as map or list
def getJsonInput(inputFile){
    def slurper = new JsonSlurper()
    def jsonFile = new File(inputFile)
    def contentText = jsonFile.text
    // parsing content of the json file to map
    def content = slurper.parseText(contentText)
    // println JsonOutput.prettyPrint(contentText)
    return content
}

def initCsvHeaders(sb,headers){
    // Adding CSV headers
    sb = addEntryPutComma(sb,"Name")
    for (header in headers) {
        sb = addEntryPutComma(sb,header)
    }
    // removing the last comma and adding a new line character
    return removeCommaAddNextLine(sb)
}

def addEntryPutComma(sb,entry){
    //  adding an entry and put comma at the end
    sb.append(entry)
    sb.append(",")
    return sb
}

def removeCommaAddNextLine(sb){
    // remove the last comma and add new line character
    sb.setLength(sb.length() - 1);
    sb.append("\n")
    return sb
}


def getHeaders(inputMap){
    // Here; assuming all testcases have same properties
    // getting first row of the properties
    // We know that that response json format is a Map containing List of Map
    // So to get inner map keys
    def keyNames = inputMap.keySet() as String[]; 
    def list; 
    for(key in keyNames){
        // getting inner list
        list = inputMap[(key)];
        if(list.size() > 0){
            // if the list size is greater than one sending back the keys of first map in the list
            return list[0].keySet() as String[];
        }
    }
    // if code reaches here it means the response is empty nothing to print in the csv file.
    return []
}


def listMaptoText(inputMap){
    // Extract just the keys from the map
    def keyNames = inputMap.keySet() as String[];   
    // println keyNames

    // map[avdxp_cict_status][0]  mapKeys
    // Get the headers for the csv file 
    def headers = getHeaders(inputMap);

    // INcase no headers found, it means that json file contains empty array
    if(headers.size() == 0){
        println "No contents found"
        return ""
    }
    // println headers

    // Using string builder to create CSV file
    StringBuilder sb = new StringBuilder()
    // Init CSV headers
    sb = initCsvHeaders(sb,headers)
    for (key in keyNames){
        // get the first list
        def listData = inputMap[(key)]
        for (mapData in listData){
            // add the first key ie  avdxp_cict_status, avdxp_ct_failed_testcases.
            sb = addEntryPutComma(sb,key)
            // get all the indexes
            def indexes = 0..(headers.size()-1)
            for(def n:indexes){
                // add entry in same order as the headers, thus removing mismatch
                sb = addEntryPutComma(sb,mapData[(headers[n])])
            }
            // removing the last comma and adding new line character
           sb = removeCommaAddNextLine(sb)
        }
    }
    // returning back the string
    return sb.toString()
}

def saveStringToCsvFile(text, outputFilename="./result.csv"){
    // creating a new CSV file if not present or overriding an existing one
    def csvFile = new File(outputFilename)
    csvFile.text = text
}

// input file name
def inputFile = "./av_dxp_android11-verify_1471.json"
// output file name
def outputFile = "./result.csv"
// Get the response parsed
def inputMap = getJsonInput(inputFile)
// convert list map to text
def responseText = listMaptoText(inputMap)
// save the string created to a CSV file
saveStringToCsvFile(responseText,outputFile)
// Print out success message
println "Success!\nPlease find csv file at ${outputFile}"
