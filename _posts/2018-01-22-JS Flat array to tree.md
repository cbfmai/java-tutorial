---
layout: post
title: Flat array to tree
categories: [Javascript]
tags: [Javascript]
fullview: false
comments: true
---

转换Js array to tree

```js
var codes =[
  {
    "name": "name1",
    "pcode": "00",
    "code": "01"
  },
  {
    "name": "name2",
    "pcode": "00",
    "code": "02"
  },
  {
    "name": "name3",
    "pcode": "00",
    "code": "03"
  },
  {
    "name": "name11",
    "pcode": "01",
    "code": "0101"
  },
  {
    "name": "name12",
    "pcode": "01",
    "code": "0102"
  },
  {
    "name": "name21",
    "pcode": "02",
    "code": "0201"
  },
  {
    "name": "name31",
    "pcode": "03",
    "code": "0301"
  }
]

function createTree(jsons, pcode) {
     let result = [];
     if (jsons != null) {
       for (let item of jsons) {
         if (item.pcode == pcode) {
           let obj = {};
           obj.value = item.code;
           obj.label = item.name;
           let childrenList = this.createTree(jsons, item.code);
           if (childrenList.length > 0) {
             obj.children = childrenList;
           }
           result.push(obj);
         }
       }
     }
     return result;
}


createTree(codes, '00');
```

```json

[
  {
    "children": [
      {
        "label": "name11",
        "value": "0101"
      },
      {
        "label": "name12",
        "value": "0102"
      }
    ],
    "label": "name1",
    "value": "01"
  },
  {
    "children": [
      {
        "label": "name21",
        "value": "0201"
      }
    ],
    "label": "name2",
    "value": "02"
  },
  {
    "children": [
      {
        "label": "name31",
        "value": "0301"
      }
    ],
    "label": "name3",
    "value": "03"
  }
]

```