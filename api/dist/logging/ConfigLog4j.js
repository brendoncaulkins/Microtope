"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
const typescript_logging_1 = require("typescript-logging");
// Taken from https://www.npmjs.com/package/typescript-logging
// Create options instance and specify 2 LogGroupRules:
// * One for any logger with a name starting with model, to log on debug
// * The second one for anything else to log on info
const options = new typescript_logging_1.LoggerFactoryOptions()
    .addLogGroupRule(new typescript_logging_1.LogGroupRule(new RegExp("model.+"), typescript_logging_1.LogLevel.Debug))
    .addLogGroupRule(new typescript_logging_1.LogGroupRule(new RegExp(".+"), typescript_logging_1.LogLevel.Info));
// Create a named loggerfactory and pass in the options and export the factory.
// Named is since version 0.2.+ (it's recommended for future usage)
exports.factory = typescript_logging_1.LFService.createNamedLoggerFactory("LoggerFactory", options);
//# sourceMappingURL=ConfigLog4j.js.map