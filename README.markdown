### SlideshowDemo

#### Latest Update
This is just a demo that uses a couple of hacks to get the desired result. I'm trying out an alternate approach which can be found in the 'development' branch.
I'm trying to use the [circular list adapter](https://github.com/ragunathjawahar/circular-list-adapter) to cycle through view. But I'm facing issues like unable to scroll manually and stuff.
The code is this branch doesn't really work, you can just see a lot of trial and error code blocks. I'm not looking into this at the moment due to current commitments. But if you can do it better, pull requests are welcome.

#### About The Demo
This is a demo which displays a finite set of images in a continous slider.
There are 2 activities for a horizontal and a vertical slideshow. This was created mainly to show the credits
of an app I worked on 'MyTaxIndia'. 

#### 1. VerticalSlideshow
This uses the ScrollView. A timer is used to increment the value of the scrollbar to keep the slider moving.
When the user touches the any of the pictures, the slider stops and zooms in the image for a second before
zooms back to continue to slide.

##### Normal Sliding
![alt text](https://github.com/blessenm/SlideshowDemo/raw/master/h1.png "Normal State")

##### Sliding stops on click and the pic zooms
![alt text](https://github.com/blessenm/SlideshowDemo/raw/master/h2.png "Image clicked and zoom")
####1. HorizontalSlideshow 
The same as above but uses the horizontal scrollview.

##### Normal Sliding
![alt text](https://github.com/blessenm/SlideshowDemo/raw/master/v1.png "Normal State")

[![Bitdeli Badge](https://d2weczhvl823v0.cloudfront.net/blessenm/androidautoscrolllistview/trend.png)](https://bitdeli.com/free "Bitdeli Badge")

