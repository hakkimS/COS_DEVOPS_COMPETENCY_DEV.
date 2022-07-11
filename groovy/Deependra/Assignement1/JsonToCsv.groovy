import groovy.json.JsonSlurper

//open and read file
def inputFile = new File("D:\\GoovyTask\\input.json")
def fileOutput = inputFile.readLines()
def jsonSlurper = new JsonSlurper()
def jsonData = jsonSlurper.parseText(fileOutput)



//Define variable
def headers = ["Name"]
def dataList = []
def resultList = []
def rows = []





//json data manuplation
jsonData.each {
    it.value.each { list_value ->

        dataList.add(it.key)

        list_value.each { key, valv ->
            if (!headers.contains(key)) {
                headers.add(key)
            }
            dataList.add(valv)
        }

        resultList.add(dataList)
        dataList = []
    }

}


//add whole data into single list
rows.add(headers)
resultList.each { data ->
    rows.add(data)
}



//write data in csv
File file = new File("D:\\GroovyTask\\output.csv")
rows.each { row ->
    file.append(row.toString().replaceAll("\\[", "").replaceAll("]", ""))
    file.append("\n")
    println row
}





