# prism-android

Prism image resizer Android library

####How do I use Prism?

``` 
Prism.withUrl(url)
.width(integerWidth).height(integerHeight)
.getUrl();
 ```

* Url must not be null or empty. 
* Must be added width or height value.
* Finally getUrl() method returns intended image url.


####Prism Features

#####Crop

```
Prism.withUrl(url)
.width(500).height(900)
.cropX(7).cropY(8).cropWidth(300).cropHeight(200)
.getUrl();
```

#####Quality

```
Prism.withUrl(baseUrl)
.width(500)
.quality(95);
```

#####Frame Background Color

In png images, you can change image background color.

```
Prism.withUrl(baseUrl)
.height(800)
.backgroundColor("010101");
```

#####No Redirect

noRedirect() method's default value is false.

```
Prism.withUrl(baseUrl)
.width(500).height(700)
.noRedirect(true)
.getUrl();
```


#####Pre-Multiplied

preMultiplied() method's default value is false.

```
Prism.withUrl(baseUrl)
.width(500).height(700)
.preMultiplied(true)
.getUrl();
```

#####Preserve Ratio

preserveRatio() method's default value is true.

```
Prism.withUrl(baseUrl)
.width(500).height(700)
.preserveRatio(false)
.getUrl();
```

#####CMD

cmd() method can contain only threer options: fit, crop, resize.

```
Prism.withUrl(baseUrl)
.width(500).height(700)
.cmd(Prism.CMD.FIT)
.getUrl();
```
 

#####OUT

out() method can contain just two options: png, jpg.

```
Prism.withUrl(baseUrl)
.width(500).height(700)
.out(Prism.OUT.PNG)
.getUrl();
```

