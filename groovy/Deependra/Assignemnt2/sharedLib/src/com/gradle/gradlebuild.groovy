package com.gradle

class gradlebuild {
    Script script
    def buildGradle(){
        script.echo("gradle build ")
        script.echo("${script.WORKSPACE}")
        def command = script.sh(returnStdout: true, script: """cd ${script.WORKSPACE}/gradleDemo/GradleDemo
        ./gradlew build  """
      )
        script.echo(command)
    }
    def gradle(){
     buildGradle()
    }
    
}
