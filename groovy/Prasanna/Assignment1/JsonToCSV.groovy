import groovy.json.JsonSlurper

def slurper = new JsonSlurper()
def json = new File("./sample_data.json")
def contentText = json.text
def content = slurper.parseText(contentText)
def columns = content.avdxp_cict_status*.keySet().flatten().unique()
def encode = { e -> e == null ? '' : e instanceof String ? /"$e"/ : "$e" }
StringBuilder sb = new StringBuilder()
sb.append(columns.collect { col -> encode( col ) }.join( ',' ))
sb.append("\n")
sb.append(content.avdxp_cict_status.collect { row ->
columns.collect { colName -> encode( row[ colName ] ) }.join( ',' )
}.join( '\n' ))
def csvFile = new File("./sample.csv")
csvFile.text = sb.toString()
