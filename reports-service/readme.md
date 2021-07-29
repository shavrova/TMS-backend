GraphiQL url:

```http://localhost:7007/graphiql```

Voyager url:

```http://localhost:7007/voyager```

TODO:
unit and integration (https://applitools.com/blog/writing-tests-graphql-apis-rest-assured/) test api

Query example:
```{
     report(id:"any") {
       id
       status
       comment
       attachment{
         id
         name
         description
         path
         report{
           id
           status
           attachment {
             id
           }
         }
       }
       
     }
     
     test(id:"some"){
       id
       name
       status
       failure{
         failureId
         videoPath
         message
         stacktrace
         screenshotPath
         test{
           id
           status
           failure{
             message
           }
         }
         
       }
     }
   }
```