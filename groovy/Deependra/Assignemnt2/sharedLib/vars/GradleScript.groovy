import com.gradle.gradlebuild

def call(){
    echo 'this is the Gradle project'
    def gradlebuild =new gradlebuild(script:this)
    gradlebuild.gradle()
}
