#!/usr/bin/env groovy # -*-groovy-*-

/**
 * This code call the apple sandbox in app purchase server to validate a receipt using Groovy Code.
 * Run this using -> groovy verifyReceipt
 * By @hackerzhut
 **/


@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.7' )
 
import groovyx.net.http.HTTPBuilder
import static groovyx.net.http.Method.POST
import static groovyx.net.http.ContentType.JSON

def url 		= "https://sandbox.itunes.apple.com" 
def iosClient 	= new HTTPBuilder( url )

iosClient.setHeaders(Accept: 'application/json')

iosClient.handler.failure = { resp, json ->
    println "[ Error: ${resp} ${json} ]"
}

def receipt = new File('receipt').text
def postBody = [ 'receipt-data':receipt ]

iosClient.request( POST, JSON ) { req ->
	
	requestContentType = JSON
	uri.path = "/verifyReceipt"
	body = postBody
	
	response.success = { resp, json ->
		println "${resp.statusLine} - ${json}"
	}
}








