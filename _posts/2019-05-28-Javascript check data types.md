---
layout: post
title: How to better check data types in javascript
categories: [Javascript]
tags: [Javascript]
fullview: false
comments: true
---

怎么在`javascript`中检查数据的类型不是一件容易的事情， 需要特殊处理一些数据的逻辑 

# Table of Contents
1. [String](#String)
2. [Number](#Number)
3. [Array](#Array)
4. [Object](#Object)
5. [Null && undefined](#Null)
6. [Boolean](#Boolean)
7. [Date](#Date)
8. [Symbol](#Symbol)
9. [Error](#Error)

## String

A `string` is always a string so this one is easy. Except if called with new (new String) typeof will instead return "object". So to also include those strings instanceof can be used.

```js
// Returns if a value is a string
function isString (value) {
  return typeof value === 'string' || value instanceof String;
}
```


## Number
From typeof more things than just an ordinary number will return "number" like NaN and Infinity. To know if a value really is a number the function isFinite is also required.

```js
// Returns if a value is really a number
function isNumber (value) {
  return typeof value === 'number' && isFinite(value);
}
```

## Array
In javascript arrays are not true arrays like in java and in other languages. They're actually objects so typeof will return "object" for them. To know if something's really an array its constructor can be compared to Array.

```js
// Returns if a value is an array
function isArray (value) {
  return value && typeof value === 'object' && value.constructor === Array;
}

// ES5 actually has a method for this (ie9+)
Array.isArray(value);
```

## Object
Many things are objects in javascript. To know if a value is an object that can have properties and be looped through, its constructor can be compared to Object. It doesn't work for objects created from classes, then the instanceof operator can be used instead.

```js
// Returns if a value is an object
function isObject (value) {
  return value && typeof value === 'object' && value.constructor === Object;
}
```

## Null
Most times you don't need to check explicitly for null and undefined since they're both falsy values. However to do it below functions does the trick.

```js
// Returns if a value is null
function isNull (value) {
  return value === null;
}

// Returns if a value is undefined
function isUndefined (value) {
  return typeof value === 'undefined';
}
```

## Boolean

For booleans typeof is enough since it returns "boolean" for both true and false.

```js
// Returns if a value is a boolean
function isBoolean (value) {
  return typeof value === 'boolean';
}
```


## Error
Errors in javascript are the same as "exceptions" in many other programming languages. They come in a couple different forms like for instance Error, TypeError and RangeError. An instanceof statement is enough for them all, but just to be extra sure we also check for the "message" property that errors have.

```js
// Returns if value is an error object
function isError (value) {
  return value instanceof Error && typeof value.message !== 'undefined';
}
```

## Date
Date isn't really a data type in javascript. But to know if something's a Date object it can be checked with instanceof.

```js
// Returns if value is a date object
function isDate (value) {
  return value instanceof Date;
}
```


## Symbol
In ES6 the new datatype Symbol was added. Nicely enough typeof returns "symbol" for it so no more logic is required.


```js
// Returns if a Symbol
function isSymbol (value) {
  return typeof value === 'symbol';
}
```