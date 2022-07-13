package com.test

class MvnBuild implements Serializable {
   def script
   def folder

	MvnBuild(script){
		this.script=script
	}

	def clean(){
		script.mvn 'clean'
	}
	def install(){
		script.mvn 'install'
	}
	def build(){
		clean()
		install()
	}
}
