package com.sharedLibraryTest

class TestClass {
    
    Script script;
    
    def sayHi() {
       script.echo("Hi from ClassTest!")  
    }

    def sayHello(name) {
        script.echo("Hello ${name}")
    }
}
