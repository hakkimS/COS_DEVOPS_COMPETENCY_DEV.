package com.sharedLibraryTest

class Building_Maven{
  
  Script script
  
  def MavenCompile(){
     script.echo("MavenCompile")
    script.echo("${script.WORKSPACE}")
   def command = script.bat(returnStdout: true, script: """cd ${script.WORKSPACE}/jenkins-control
   mvn compile
   """)
    script.echo(command)
  }
  
  def MavenPackages(){
    script.echo("MavenPackages")
    def command = script.bat(returnStdout: true, script: """cd ${script.WORKSPACE}/jenkins-control
    mvn package
   """)
    script.echo(command)
  }
  
  def RunJavaProject(){
    script.echo("RunJavaProject")
    def command = script.bat(returnStdout: true, script: """cd ${script.WORKSPACE}/jenkins-control
    java -jar target/Maven_Project_demo-1.0-SNAPSHOT.jar
   """)
    script.echo(command)
  }
  
def Maven(){
  MavenCompile()
  MavenPackages()
  RunJavaProject()
  
}
}
