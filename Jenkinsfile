// Scripted pipeline 
//node{
//	stage('Test'){
		echo "Test"
//	}
	
//	stage('build'){
//		echo "build"
//	}
//}

// Declarative Pipeline

pipeline {
    agent any
    steps{
        step('clean stage'){
            echo "clean stage"
            sh 'mvn clean'
        }

        step('compile stage'){
            echo "compile stage"
            sh 'mvn compile'
        }

        step('build stage'){
            echo "build stage"
            sh 'mvn package'
        }
    }
}
