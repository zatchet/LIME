Overview: \
The GUI is laid out with the loading and saving operations on the top, the image in the middle,
and the image operations on the bottom of the panel. All operations are also accessible from
the menu in the top left corner of the panel. To process an image, first load an image or
create an image by pressing execute to run a script file, load to load an image, or create
checkerboard to create a checkerboard image. Instructions for how to use these operations will be
explained in detail below. The current layer of the image that is being viewed and operated
on is displayed below the operation buttons at the bottom of the panel. Error messages
are displayed via pop-ops for invalid operations. Please allow a few seconds for the image
to update after an operation.

Supported Operations:
1. Loading an image \
   Load an image by pressing the load button, or by choosing load from the menu. A file
   chooser window will appear. Choose the file you wish to load, or the directory of the 
   layered image you wish to load. Note: Only load layered images that were saved by this
   program. 
   
2. Saving an image \
   To save an image, press the save button or choose save from the menu. Then, choose
   whether you would like to save all layers of the image or only the topmost visible
   layer from the pop-op options. Then, enter the name of the file in the pop-op window
   asking "What do you want to call this project?". Then, from the file chooser, choose
   the DIRECTORY where you want to save the file to. Finally, choose the image format
   of the image from the choices in the final pop-up window.
   
3. Executing operation from a script file
   To perform all the supported operations by running a script file, press the execute button
   or choose execute from the menu. Then, choose the script file to run from the file
   chooser that appears. The image that is produced will appear and any error messages
   will be displayed via pop-ups.
4. Adding a layer to an image from an image file \
   To add a layer that is another image, press the Add from image button or choose add layer
   -> from image from the menu. Then, select the filetype of the image from the pop-op.
5. Adding a layer that is a copy of the top layer \
   To add a layer that is a copy of the top layer of the image, press the Add copy button 
   button or choose add layer -> from copy from the menu. Then, select the filetype of the image 
   from the pop-op.
6. Creating a checkerboard image \
   To create a checkerbaord image, click the create checkerboard button or choose
   it from the menu. This is the same
   as loading a checkerboard image. Enter the dimensions of the checkerboard you want
   in the pop-up and press OK to see the image and process it.
7. Adding a layer to an image that is a checkerboard image \
   To add a checkerbaord layer to the image, click the add checkerboard button or choose
   it from the menu. Enter the dimensions of the checkerboard you want
   in the pop-up and press OK.
   
8. Blurring the current image \
   To blur the current layer of the image, press the blur button or choose it from the menu.
9. Converting an image to sepia tones \
   To convert the image to sepia, press the sepia button or choose it from the menu.
10. Sharpening an image \
    To sharepen the image, press the sharpen button or choose it from the menu. It will take a 
    few seconds for the image to update.
11. Converting an image to greyscale tones \
    To convert the image to greyscale, press the greyscale button or choose it from the menu.
12. Removing a layer of the image \
    To remove a layer of the image, press the remove button or choose it 
    from the menu. Then, choose which layer to remove from the pop-up.
13. Making a layer of the image visible \
    To make a layer of the image invisible, click the make invisible button or choose it from
    the menu. Then, choose which layer to make invisible from the pop-up.
14. Making the current layer of the image invisible \
    To make a layer of the image visible, click the make visible button or choose it from
    the menu. Then, choose which layer to make visible from the pop-up.
15. Reverting the previous image operation \
    To revert the image to its state before the previous image operation, click the revert button
    or choose it from the menu.

............................................................................

Note on sample scripts:
- script1.txt demonstrates perfectly entered commands that process an image. You'll know it
  worked as intended if nothing is outputted.
- script2.txt demonstrates the error handling in our program. It contains bad commands that
  are not accepted as input and error messages are outputted.

Supported Commands:
**note that the root directory for all file paths is the project folder.
**note that create, load, or createCheckerboard must be called before any of the other commands
1. "create" followed by the file extension and the filepath will create a new multi layered image
   starting with the given image file. (e.g. "create ppm Koala.ppm" or "create png
   somefolder/someimage.png")

2. "load" followed by the image file extension and the path of the project you wish to load
   will load in the project (e.g. "load png project" or "load jpg res/otherproject"). The project
   name is the name of the folder that holds all the layers as well as the txt file.

3. "createCheckerboard" followed by two ints will create a multi layered image starting with
   a checkerboard of the given dimensions. (e.g. "createCheckerboard 10 10")

4. "sepia", "blur", "sharpen", or "greyscale" followed by an int will apply the respective operation
   to the layer at the given index. Indexing starts at 0. (e.g. "sepia 2")

5. "remove" followed by an int will remove the layer at the sepcified index, (e.g. "remove 2")
6. "export" followed by the file extension and the file name will export the top-most visible layer
   (e.g. "export png Koala.png)

7. "exportAll" followed by the file extension and the project name/name of the folder you wish everthing to be
   located in will export all layers, regardless of visibility (e.g. "exportAll ppm layeredKoala")

8. "make" followed by either "visible" or "invisible" and an int will set the layer at the given
   index to the specified visibility (e.g. "make visible 0" or "make invisible 3")
   **Note: layers can only be added if they are the same dimensions as the current project
   **Note: blank layers are not supported. Every layer always contains/is an image

9. "addCopy" will add a layer to the top of the current project that is a copy of the current top-
   most image.

10. "addFromImage" followed by the file extension and file name will add the given image as a layer
    to the current project (e.g. "addFromImage jpeg newLayer.jpeg")

11. "addCheckerboard" followed by two ints will add a new checkerboard image with the given
    dimensions as a layer to the current project (e.g. "addCheckerboard 5 5")
    
