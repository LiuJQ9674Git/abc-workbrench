=begin
Swagger Petstore

This is a sample server Petstore server.  You can find out more about Swagger at <a href=\"http://swagger.io\">http://swagger.io</a> or on irc.freenode.net, #swagger.  For this sample, you can use the api key \"special-key\" to test the authorization filters

OpenAPI spec version: 1.0.0
Contact: apiteam@swagger.io
Generated by: https://github.com/swagger-api/swagger-codegen.git

License: Apache 2.0
http://www.apache.org/licenses/LICENSE-2.0.html

Terms of Service: http://swagger.io/terms/

=end

# Common files
require 'petstore/api_client'
require 'petstore/api_error'
require 'petstore/version'
require 'petstore/configuration'

# Models
require 'petstore/models/user'
require 'petstore/models/category'
require 'petstore/models/pet'
require 'petstore/models/tag'
require 'petstore/models/order'

# APIs
require 'petstore/api/user_api'
require 'petstore/api/pet_api'
require 'petstore/api/store_api'

module Petstore
  class << self
    # Customize default settings for the SDK using block.
    #   Petstore.configure do |config|
    #     config.username = "xxx"
    #     config.password = "xxx"
    #   end
    # If no block given, return the default Configuration object.
    def configure
      if block_given?
        yield(Configuration.default)
      else
        Configuration.default
      end
    end
  end
end