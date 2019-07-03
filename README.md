
# react-native-webim

## Getting started

`$ npm install react-native-webim --save`

### Mostly automatic installation

`$ react-native link react-native-webim`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-webim` and add `RNWebim.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libRNWebim.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import com.reactlibrary.RNWebimPackage;` to the imports at the top of the file
  - Add `new RNWebimPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-webim'
  	project(':react-native-webim').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-webim/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-webim')
  	```

#### Windows
[Read it! :D](https://github.com/ReactWindows/react-native)

1. In Visual Studio add the `RNWebim.sln` in `node_modules/react-native-webim/windows/RNWebim.sln` folder to their solution, reference from their app.
2. Open up your `MainPage.cs` app
  - Add `using Webim.RNWebim;` to the usings at the top of the file
  - Add `new RNWebimPackage()` to the `List<IReactPackage>` returned by the `Packages` method


## Usage
```javascript
import RNWebim from 'react-native-webim';

// TODO: What to do with the module?
RNWebim;
```
  