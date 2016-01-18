/**
 * @provideModule Dialog
 */

'use strict';

import { Alert, AlertIOS, Platform, NativeModules } from 'react-native';

function alert(title, message, buttons) {
  if (Platform.OS === 'ios') {
    AlertIOS.alert(title, message, buttons);
  } else {
    Alert.alert(title, message, buttons);
  }
}

function prompt(title, message, buttons) {
  if (Platform.OS === 'ios') {
    AlertIOS.prompt(title, message, buttons);
  } else {
    buttons = buttons.reverse().reduce((prevItem, currentItem, index) => {
      let key = -index - 1;
      prevItem[key] = currentItem;
      return prevItem;
    }, {});

    let errorCallback = (buttonId, text) => {
      consolo.log("error");
    };
    let successCallback = (buttonId, text) => {
      buttonId = buttonId.toString();
      buttons[buttonId].onPress(text);
    };

    NativeModules.PromptAndroid.show(title, message, buttons, errorCallback, successCallback);
  }
}

module.exports = {
  alert: alert,
  prompt: prompt
};
