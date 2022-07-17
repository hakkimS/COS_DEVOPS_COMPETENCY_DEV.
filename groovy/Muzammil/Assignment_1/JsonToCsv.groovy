import groovy.json.*

def data = new JsonSlurper().parse(new File('av_dxp_android11-verify_1471.json'))
def columns = data.avdxp_cict_status*.keySet().flatten().unique()
// Wrap strings in double quotes, and remove nulls
def encode = { e -> e == null ? '' : e instanceof String ? /"$e"/ : "$e" }
//
def newFile = new File("C:/Users/mumo274193/IdeaProjects/untitled/ConJson.csv")
// Print all the column names
def col =columns.collect { c -> encode( c ) }.join( ',' )
// Then create all the rows
def dat= data.avdxp_cict_status.collect { row ->
   // A row at a time
    columns.collect { colName -> encode( row[ colName ] ) }.join( ',' )
}.join( '\n' )
def file = new File('CsvFile.csv')
file.append(col)
file.append(dat)