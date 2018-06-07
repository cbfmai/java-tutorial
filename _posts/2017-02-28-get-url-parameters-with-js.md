---
layout: post
title: Get the url parameters with native javascript
categories: [Javascript]
tags: [Javascript]
fullview: false
comments: true
---

Sometimes you need to get the parameters with javascript in the url, like bellow:
`http://demo.test.com?v1=s&v2=234`, we want to get the value of `v1` and `v2`, how?

Easily, we can use the `location.search` with the code.

```js
var QueryString = function() {
    // This function is anonymous, is executed immediately and
    // the return value is assigned to QueryString!
    var query_string = {};
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i = 0; i < vars.length; i++) {
        var pair = vars[i].split("=");
        // If first entry with this name
        if (typeof query_string[pair[0]] === "undefined") {
            query_string[pair[0]] = decodeURIComponent(pair[1]);
            // If second entry with this name
        } else if (typeof query_string[pair[0]] === "string") {
            var arr = [ query_string[pair[0]],
                    decodeURIComponent(pair[1]) ];
            query_string[pair[0]] = arr;
            // If third or later entry with this name
        } else {
            query_string[pair[0]].push(decodeURIComponent(pair[1]));
        }
    }
    return query_string;
}();
```

After that we can use `QueryString.v1` to get the value of it.