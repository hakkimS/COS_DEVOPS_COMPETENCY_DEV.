import com.test.mvnBuild

def call() {
	def mvnbuild = new mvnBuild(this)
	mvnbuild.build()
}
