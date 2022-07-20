import groovy.json.JsonSlurper

def inputFile = new File("D:\\Assignement1\\input.json")
def fileOutput = inputFile.readLines()
def jsonSlurper = new JsonSlurper()
def jsonData = jsonSlurper.parseText(fileOutput)

def columns = Json_Data.avdxp_cict_status*.keySet().flatten().unique()
def csv1 = new File('Out1.csv')

//Creating header
csv1.append(columns.collect { c -> c }.join( ',' ))
csv1.append("\n")

//Creating all the rows
Json_Data.avdxp_cict_status.collect { row ->
    csv1.append(columns.collect { colName -> row[colName] }.join( ',' ))
    csv1.append("\n")
}


def header_row = []
def header_list = []
def data_rows = []
def data_list = []

//Creating all rows
Json_Data.each { field,row ->
    row.each { set ->
        def value_list = []
        set.each { key,value ->
            if (!header_list.contains(key)) {
                header_list.add(key)
            }
            if (header_list.contains(key)){
                value = value instanceof String ? '"' + value + '"' : value
                value_list.add(value)
            }
        }
        data_list.add(value_list.join(','))
    }
    header_row = header_list.join(',')
    data_rows = data_list.join('\n')
}

//Write data in CSV
File csv = new File("Out2.csv")
csv.write(header_row)
csv << '\n' + data_rows

//Printing data
println(header_row)
println(data_rows)
