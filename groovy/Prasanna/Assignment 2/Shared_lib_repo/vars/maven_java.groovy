def call(String mvnUrl) {
pipeline {
       agent any
       tools {
           maven 'Maven 3.8.6'
           jdk 'JDK 1.8'
       }
       stages {
	   stage("Welcome") {
               steps {
		           echo 'Welcome DevOps Team'
                   welcome 'Prasanna'
               }
           }
           stage("Check Tool versions") {
               steps {
                   sh "mvn --version"
                   sh "java -version"
               }
           }
           stage("Get Maven project") {
		   steps {
                   git branch: 'master',
				   credentialsId: 'Add your credentialsId',
                   url: "${mvnUrl}"
		      }
           }
           stage("Clean workspace") {
               steps {
                   sh "mvn clean"
               }
           }
           stage("Run Test") {
              steps {
                   sh "mvn test"
               }
           }
           stage("Packing Application") {
               steps {
                   sh "mvn package -DskipTests"
               }
           }
       }
   }
}

