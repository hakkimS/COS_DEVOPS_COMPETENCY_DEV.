import com.sharedLibraryTest.BuildClass

def call(int buildNumber,currentJobName) {
  def classBuild = new BuildClass(script:this)
  if (buildNumber % 2 == 0) {
    pipeline {
      agent any
      stages {
        stage('Even Build') {
          steps {
            script{
             classBuild.EvenBuild(currentJobName,buildNumber)
            }
          }
        }
      }
    }
  } else {
    pipeline {
      agent any
      stages {
        stage('Odd Build') {
          steps {
            script{
            classBuild.OddBuild(currentJobName,buildNumber)
            }
          }
        }
      }
    }
  }
}
  
