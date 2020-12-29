

import com.pzz.boot.ioc.GroovyIOC
import com.pzz.boot.ioc.XMLIOCStudy
import com.pzz.boot.ioc.methodInject.Command
import com.pzz.boot.ioc.methodInject.Commandmannager

beans {

    xmlioc(XMLIOCStudy) { }
    groovyIOC(GroovyIOC) {
        s = "hello world! pzz"
    }
//    myCommand(Command){}
    commandmannager(Commandmannager) {}


   /* myService(MyService) {
        nestedBean = { AnotherBean bean ->
            dataSource = dataSource
        }
    }*/
}