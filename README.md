Please see the design presentation: [Design Presentation](presentation.pdf)

How to use: \
After running the main method, there are three options for how to run the program that are launched by entering a simple command: 

1. "-script filename" \
   Replace "filename" with the name of the script file to run for processing images as in Assignment 6.

2. "-text" \
   This option allows the user to enter commands line by line using the scripting functionality developed in Assignment 6.

3. "-interactive" \
   This option launches the GUI that displays the image and shows the image operations as they are applied using the buttons or menu. A detailed description for how to use the GUI to process images can be found in the USEME file.

Complete Parts: \
All required parts of the assignment have been completed. All image operations that we have developed are supported from the GUI.

Design Changes: \
We did not change anything from our previous project, we added an interface and two classes for a new controller and new view. These are InteractiveView, Graphical View and GraphicController.

..............................................................................

DESIGN OVERVIEW

We designed everything in this project with the open for extension, closed for modification principle in mind. For example, we used the factory pattern to create images and we used the Command Design Pattern in the controller. We also spent a lot of time thinking about what each class and interface should be responsible for and what everything represents. We stuck to the “one class per idea” approach to avoid spaghetti code and tight coupling between classes. For this assignment, we added a Controller package for IO, a simple View that renders messages to user.

Complete Functionality:

All required features for this assignment are complete. This includes supporting processing layered images, importing and exporting layered images in the PPM, PNG, and JPEG file formats. We still have the ability to perform filtering and color transformations on images and it is possible to add more of these in the future. Our design allows for images to be loaded in one format and saved in another and vice versa. We also include the ability for the user to run our image processing tool by providing a script.

Changes:

The main change we made from the previous assignment was creating a controller and moving the export functionality out of the model. Otherwise, we only added additional classes and interfaces.

Interfaces and Classes:

ImageEditor: The model interface representing the image processing application. Images can be provided directly as an image object, parsed in from a file given a filename, or created programatically given the number of rows and columns of the checkerboard pattern to generate. Support is included for blurring and sharpening an image, converting the image to greyscale or sepia color tones, applying a custom filter or color transformation to the image, reverting to a previous version of the image and exporting the image.

SimpleImageEditor: Class implementing the model interface. In this implementation, the supported operations can be applied to a single image provided by the constructor.

Image: Interface representing the data for an image. Operations include getting a specific pixel in an image given its position, getting the number of rows of pixels in the image, and getting the number of columns in the image.

ImageImpl: Class implementing the image interface.

Pixel: Class representing a pixel, the basic component of an image. A pixel consists of a color with channels that alter the appearance of the image when changed.

Color: Interface representing a color with three channels: red, green, and blue. Support is provided for getting the value of each channel as well as the color represented as a String.

ClampedColor: Color implementation that clamps its color channels. A class invariant is that the value for each color channel is greater than zero and less than or equal 255.

ImageOperation: Represents an operation to be applied to an image.

ArrayOperand: Represents the data to be used in an operation to perform on an image. For example, the kernel to apply in a filter or transformation matrix to multiply by in a color transformation.

AbstractColorTransformation: An ImageOperation that uses a TransformationMatrix to perform the operation on the image. The matrix is multiplied by the matrix of the color channels of each image through matrix multiplication. This class can be instantiated to apply a custom transformation to an image.

TransformationMatrix: An ArrayOperand representing the matrix to multiply the color channels of a pixel by in a color transformation. Therefore, the matrix must have three columns.

Sepia: Class representing the color transformation that converts the image to sepia tones

Greyscale: Class representing the color transformation that converts the image to monochrome.

AbstractFilter: Class representing an ImageOperation that uses a Kernel to perform filtering operations on an image. The kernel is applied to each pixel in the image to produce the filtered result. This class can be instantiated to apply a custom filter to the image.

Kernel: The ArrayOperand representing the matrix to be applied to each pixel in an image. The kernel must have an odd number of rows and columns so that the center element of the matrix is applied to each pixel.

ImageBlurrer: Class representing the blur filter that slightly blurs the image. For a more noticeable effect, this filter may need to be applied multiple times.

ImageSharpener: Class representing the sharpen filter that sharpens the image.

ImageCreator: Interface representing the factory classes that create images from files given the file name. Future implementations of this interface will support more filetypes to create images from.

ImageFromPPM: Class that creates an image from a “plain” .ppm file.

ImageExporter: Interface for exporting an image to a file. Future implementations of this interface will support additional filetypes.

PPM: Class that exports an image to a “plain” ppm file.


New Packages, Interfaces, and classes in Assignment 6:

package controller: This package contains classes and interfaces for importing and exporing simple images and layered images. It also
                    contains code supporting running the layered image processor with a script.

package controller.commands: This package contains interfaces and classes implementing the command design pattern for image
                             processing commands in the controller.

package controller.FileUtil: Contains helpful methods for exporting and importing files.

package view: This package consists of code for a simple view that renders messages to the user.

class model.LayeredImageEditor: This interface supporting processing layered images. Each layer in th image consists of a simple image processor, so all functionality for processing images from Assignment 5 is supported for each layer in assignment 6.


IMAGE CITATIONS

Images are stock images from Pixabay. Here is their terms of use:

All content on Pixabay can be used for free for commercial and noncommercial use across print and digital, except in the cases mentioned in "What is not allowed".

Attribution is not required. Giving credit to the contributor or Pixabay is not necessary but is always appreciated by our community.

You can make modifications to content from Pixabay.

Frog image: https://pixabay.com/photos/frog-eyes-pond-amphibians-nature-6316284/

Chameleon image: https://pixabay.com/photos/chameleon-head-green-lizard-6307349/



