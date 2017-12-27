var exec = require('cordova/exec');

exports.getEpcList = function (success, error) {
    exec(success, error, 'UHF', 'getEpcList', []);
};
exports.setOutputPower = function (arg0, success, error) {
    exec(success, error, 'UHF', 'setOutputPower', [arg0]);
};
