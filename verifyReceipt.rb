# This code call the apple sandbox in app purchase server to validate a receipt using Ruby Code.
# Run this using "ruby verifyReceipt.rb"
# By @sauloarruda (http://twitter.com/sauloarruda)
 
require 'net/http'
 
# This core reads an file called receipt (see an example bellow)
params_json = "{ \"receipt-data\": \"#{open("./receipt").read}\"  }"
 
# Use net/http to post to apple sandbox server
uri = URI("https://sandbox.itunes.apple.com") # Use "https://buy.itunes.apple.com" for production
Net::HTTP.start(uri.host, uri.port, :use_ssl => uri.scheme == 'https') do |http|
  response = http.post('/verifyReceipt', params_json)
  # Puts the result! (see an example below - result.json)
  puts response.body
end