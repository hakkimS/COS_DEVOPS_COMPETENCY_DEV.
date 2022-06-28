package com.sharedLibraryTest

class BuildClass {
    Script script;
    def EvenBuild(JobName,BuildNumber) {
        script.echo("${JobName} Job's Current Build is Even and the build Number is ${BuildNumber}")      
    }

    def OddBuild(JobName,BuildNumber) {
        script.echo("${JobName} Job's Current Build is Odd and the build Number is ${BuildNumber}")
    }
}
