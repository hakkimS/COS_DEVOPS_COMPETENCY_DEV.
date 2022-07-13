import groovy.json.JsonSlurper

def jsonSlurper = new JsonSlurper()
json_text = jsonSlurper.parse(new File('sample.json'))
header = []
temp_list = []
lst = []

flag = 0
json_text.each {
    uno_array = it //Parent array iterating
    uno_array.each {val -> main_topic = val.key //Storing the topic/key
        uno_list = val.value
        uno_list.each {val2 -> sub_array = val2
            sub_array.each {val3 -> each_value = val3.value
                if(flag == 0) {
                        each_key = val3.key
                        header.add(main_topic+"_"+each_key)
                    }
                temp_list.add(each_value)
            }
            flag = 1
            lst.add(temp_list)
            temp_list = []
        }
    }
    flag = 0
}

println(header)
lst.each {println(it)}

File lstFile = new File("out.csv")
lstFile.withWriter{ out ->
    header.each {out.print it+","}
    out.println("")
}
lst.each { val ->
    lstFile.withWriterAppend { out ->
        val.each { out.print it + "," }
        out.println("")
    }
}
