# react-native-prompt-android
React Native prompt for Android.

## Screenshot
<img src="http://onethe.be/download/Two_buttons.png" alt="Two Buttons" width="300px" />
<img src="http://onethe.be/download/Three_buttons.png" alt="Three Buttons" width="300px" />

## Usage
Button sequence is same as react native doc, https://facebook.github.io/react-native/docs/alert.html#android
* If you specify one button, it will be the 'positive' one (such as 'OK')
* Two buttons mean 'negative', 'positive' (such as 'Cancel', 'OK')
* Three buttons mean 'neutral', 'negative', 'positive' (such as 'Later', 'Cancel', 'OK')

```java
import com.ocleo1.dialog.PromptPackage; // <----- import

public class MainActivity extends Activity implements DefaultHardwareBackBtnHandler {
  ......

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mReactRootView = new ReactRootView(this);

    mReactInstanceManager = ReactInstanceManager.builder()
      .setApplication(getApplication())
      .setBundleAssetName("index.android.bundle")
      .setJSMainModuleName("index.android")
      .addPackage(new MainReactPackage())
      .addPackage(new PromptPackage()) // <------ add here
      .setUseDeveloperSupport(BuildConfig.DEBUG)
      .setInitialLifecycleState(LifecycleState.RESUMED)
      .build();

    mReactRootView.startReactApplication(mReactInstanceManager, "App", null);

    setContentView(mReactRootView);
  }
  ......
}
```

## Example
```js
var Dialog = require('./Dialog');

var App = React.createClass({
  _openDialog() {
    Dialog.prompt('THIS IS TITLE', 'You can input now', [
      {text: 'Later', onPress: (text) => {console.log(text)}},
      {text: 'Cancel', onPress: (text) => {console.log(text)}},
      {text: 'OK', onPress: (text) => {console.log(text)}}
    ]);
  },
  render: function() {
    return (
      <TouchableOpacity onPress={this._openDialog}>
        <Text>Open Dialog</Text>
      </TouchableOpacity>
    );
  }
});

AppRegistry.registerComponent('App', () => App);
```
