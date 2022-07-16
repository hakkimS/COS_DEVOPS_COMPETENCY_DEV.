import groovy.json.JsonSlurper

def jsonSlurper = new JsonSlurper()
def Tables = jsonSlurper.parse(new File('n.json') )

File csv

def file_name = ''
def field_row = ''
def rows = ''
def values = ''

def field_lst = []
def val_list = []
def row_list = []

Tables.each { field,row ->
    file_name = field
    field_lst = []
    rows = []
    row_list = []
    row.each { set ->
        val_list = []
        set.each { key,value ->
            if (!field_lst.contains(key)) {
                    field_lst.add(key)
                }
            value = value instanceof String ? '"' + value + '"' : value
            if (field_lst.contains(key)){
                  val_list.add(value)
                }
        }
        values = val_list.join(',')
        row_list.add(values)
    }

    field_row = field_lst.join(',')
    rows = row_list.join('\n')
    csv = new File("${file_name}.csv")
    csv.write(field_row)
    csv << '\n' + rows
    println field_row
    println rows
}