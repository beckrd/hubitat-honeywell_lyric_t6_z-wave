/*
 *  Copyright 2015 SmartThings
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
	definition (name: "Honeywell T6 Pro", namespace: "prjct92eh2", author: "prjct92eh2", mnmn:"SmartThings", vid: "SmartThings-smartthings-Z-Wave_Thermostat") {
		capability "Actuator"
		capability "Temperature Measurement"
		capability "Relative Humidity Measurement"
		capability "Thermostat"
		capability "Refresh"
		capability "Sensor"
		capability "Health Check"
		capability "Thermostat Heating Setpoint"
		capability "Thermostat Cooling Setpoint"
		capability "Thermostat Operating State"
		capability "Thermostat Mode"
		capability "Thermostat Fan Mode"
		
		attribute "thermostatFanState", "string"

		command "switchMode"
		command "switchFanMode"
		command "lowerHeatingSetpoint"
		command "raiseHeatingSetpoint"
		command "lowerCoolSetpoint"
		command "raiseCoolSetpoint"
		command "poll"

		//fingerprint deviceId: "0x08"
		//fingerprint inClusters: "0x43,0x40,0x44,0x31"
		//fingerprint mfr:"0039", prod:"0011", model:"0001", deviceJoinName: "Honeywell Z-Wave Thermostat"
		//fingerprint mfr:"008B", prod:"5452", model:"5439", deviceJoinName: "Trane Thermostat"
		//fingerprint mfr:"008B", prod:"5452", model:"5442", deviceJoinName: "Trane Thermostat"
		//fingerprint mfr:"008B", prod:"5452", model:"5443", deviceJoinName: "American Standard Thermostat"
		fingerprint mfr:"0039", prod:"0011", model:"0008", deviceJoinName: "Honeywell T6 Pro Z-Wave Thermostat"//Added by prjct92eh2
      fingerprint mfr:"0057", prod:"0017", model:"0008", deviceJoinName: "Honeywell T6 Pro Z-Wave Thermostat"//Added by beckrd
	}

	tiles {
		multiAttributeTile(name:"temperature", type:"generic", width:3, height:2, canChangeIcon: true) {
			tileAttribute("device.temperature", key: "PRIMARY_CONTROL") {
				attributeState("temperature", label:'${currentValue}°', icon: "st.alarm.temperature.normal",
					backgroundColors:[
							// Celsius
							[value: 0, color: "#153591"],
							[value: 7, color: "#1e9cbb"],
							[value: 15, color: "#90d2a7"],
							[value: 23, color: "#44b621"],
							[value: 28, color: "#f1d801"],
							[value: 35, color: "#d04e00"],
							[value: 37, color: "#bc2323"],
							// Fahrenheit
							[value: 40, color: "#153591"],
							[value: 44, color: "#1e9cbb"],
							[value: 59, color: "#90d2a7"],
							[value: 74, color: "#44b621"],
							[value: 84, color: "#f1d801"],
							[value: 95, color: "#d04e00"],
							[value: 96, color: "#bc2323"]
					]
				)
			}
		  tileAttribute("device.humidity", key: "SECONDARY_CONTROL") {
				attributeState "humidity", label:'${currentValue}%'
			}
		}
		standardTile("mode", "device.thermostatMode", width:2, height:2, inactiveLabel: false, decoration: "flat") {
			state "off", action:"switchMode", nextState:"...", icon: "st.thermostat.heating-cooling-off"
			state "heat", action:"switchMode", nextState:"...", icon: "st.thermostat.heat"
			state "cool", action:"switchMode", nextState:"...", icon: "st.thermostat.cool"
			state "auto", action:"switchMode", nextState:"...", icon: "st.thermostat.auto"
			state "emergency heat", action:"switchMode", nextState:"...", icon: "st.thermostat.emergency-heat"
			state "...", label: "Updating...",nextState:"...", backgroundColor:"#ffffff"
		}
		standardTile("fanMode", "device.thermostatFanMode", width:2, height:2, inactiveLabel: false, decoration: "flat") {
			state "auto", action:"switchFanMode", nextState:"...", icon: "st.thermostat.fan-auto"
			state "on", action:"switchFanMode", nextState:"...", icon: "st.thermostat.fan-on"
			state "circulate", action:"switchFanMode", nextState:"...", icon: "st.thermostat.fan-circulate"
			state "...", label: "Updating...", nextState:"...", backgroundColor:"#ffffff"
		}
		standardTile("lowerHeatingSetpoint", "device.heatingSetpoint", width:2, height:1, inactiveLabel: false, decoration: "flat") {
			state "heatingSetpoint", action:"lowerHeatingSetpoint", icon:"st.thermostat.thermostat-left"
		}
		valueTile("heatingSetpoint", "device.heatingSetpoint", width:2, height:1, inactiveLabel: false, decoration: "flat") {
			state "heatingSetpoint", label:'${currentValue}° heat', backgroundColor:"#ffffff"
		}
		standardTile("raiseHeatingSetpoint", "device.heatingSetpoint", width:2, height:1, inactiveLabel: false, decoration: "flat") {
			state "heatingSetpoint", action:"raiseHeatingSetpoint", icon:"st.thermostat.thermostat-right"
		}
		standardTile("lowerCoolSetpoint", "device.coolingSetpoint", width:2, height:1, inactiveLabel: false, decoration: "flat") {
			state "coolingSetpoint", action:"lowerCoolSetpoint", icon:"st.thermostat.thermostat-left"
		}
		valueTile("coolingSetpoint", "device.coolingSetpoint", width:2, height:1, inactiveLabel: false, decoration: "flat") {
			state "coolingSetpoint", label:'${currentValue}° cool', backgroundColor:"#ffffff"
		}
		standardTile("raiseCoolSetpoint", "device.heatingSetpoint", width:2, height:1, inactiveLabel: false, decoration: "flat") {
			state "heatingSetpoint", action:"raiseCoolSetpoint", icon:"st.thermostat.thermostat-right"
		}
		standardTile("thermostatOperatingState", "device.thermostatOperatingState", width: 2, height:2, decoration: "flat") {
			state "thermostatOperatingState", label:'${currentValue}', backgroundColor:"#ffffff"
		}
		standardTile("refresh", "device.thermostatMode", width:2, height:2, inactiveLabel: false, decoration: "flat") {
			state "default", action:"refresh.refresh", icon:"st.secondary.refresh"
		}
		valueTile("humidity", "device.humidity", width: 2, height: 2, inactiveLabel: false) {
			state "humidity", label:' ${currentValue}%', icon:"st.Weather.weather12", backgroundColors: [
				[value: 20, color: "#ffe700"],
				[value: 30, color: "#d6ff00"],
				[value: 45, color: "#3cff00"],
				[value: 60, color: "#00ffb8"],
				[value: 80, color: "#00dfff"]
			]
		}        
		main "temperature"
		details(["temperature", "lowerHeatingSetpoint", "heatingSetpoint", "raiseHeatingSetpoint", "lowerCoolSetpoint",
				"coolingSetpoint", "raiseCoolSetpoint", "mode", "fanMode", "humidity", "thermostatOperatingState", "refresh"])
	}
}

def installed() {
	// Configure device
	def cmds = [new hubitat.device.HubAction(zwave.associationV1.associationSet(groupingIdentifier:1, nodeId:[zwaveHubNodeId]).format()),
			new hubitat.device.HubAction(zwave.manufacturerSpecificV2.manufacturerSpecificGet().format())]
	sendHubCommand(cmds)
	runIn(3, "initialize", [overwrite: true])  // Allow configure command to be sent and acknowledged before proceeding
}

def updated() {
	// If not set update ManufacturerSpecific data
	if (!getDataValue("manufacturer")) {
		sendHubCommand(new hubitat.device.HubAction(zwave.manufacturerSpecificV2.manufacturerSpecificGet().format()))
		runIn(2, "initialize", [overwrite: true])  // Allow configure command to be sent and acknowledged before proceeding
	} else {
		initialize()
	}
}

def initialize() {
	log.debug "Initializing the thermostat"
	// Device-Watch simply pings if no device events received for 32min(checkInterval)
	sendEvent(name: "checkInterval", value: 2 * 15 * 60 + 2 * 60, displayed: false, data: [protocol: "zwave", hubHardwareId: device.hub.hardwareID])
	unschedule()
	//Honeywell T6 Pro needs to be polled to bring in updated setpoints
	log.debug "Scheduling poll every 5 minutes"
	runEvery5Minutes("poll")  // This is not necessary for non-T6 Honeywell Z-wave, but could be for other Z-wave thermostats
	pollDevice()
}

def parse(String description)
{
	def result = null
	if (description == "updated") {
	} else {
		def zwcmd = zwave.parse(description, [0x42:1, 0x43:2, 0x31: 3])
		if (zwcmd) {
			result = zwaveEvent(zwcmd)
		} else {
			log.debug "$device.displayName couldn't parse $description"
		}
	}
	if (!result) {
		return []
	}
	return [result]
}

// Event Generation
def zwaveEvent(hubitat.zwave.commands.thermostatsetpointv2.ThermostatSetpointReport cmd) {
	def cmdScale = cmd.scale == 1 ? "F" : "C"
	def setpoint = getTempInLocalScale(cmd.scaledValue, cmdScale)
	def unit = getTemperatureScale()
	switch (cmd.setpointType) {
		case 1:
			sendEvent(name: "heatingSetpoint", value: setpoint, unit: unit, displayed: false)
			updateThermostatSetpoint("heatingSetpoint", setpoint)
			log.debug "${device.displayName} heat is set to ${setpoint}"
			break;
		case 2:
			sendEvent(name: "coolingSetpoint", value: setpoint, unit: unit, displayed: false)
			updateThermostatSetpoint("coolingSetpoint", setpoint)
			log.debug "${device.displayName} cool is set to ${setpoint}"
			break;
		default:
			log.debug "unknown setpointType ${cmd.setpointType}"
			return
	}
	// So we can respond with same format
	state.size = cmd.size
	state.scale = cmd.scale
	state.precision = cmd.precision
	// Make sure return value is not result from above expresion
	return 0
}

def zwaveEvent(hubitat.zwave.commands.sensormultilevelv3.SensorMultilevelReport cmd) {
	def map = [:]
	if (cmd.sensorType == 1) {
		map.value = getTempInLocalScale(cmd.scaledSensorValue, cmd.scale == 1 ? "F" : "C")
		map.unit = getTemperatureScale()
		map.name = "temperature"
		log.debug "${device.displayName} temperature is ${map.value}"
		updateThermostatSetpoint(null, null)
	} else if (cmd.sensorType == 5) {
		map.value = cmd.scaledSensorValue
		map.unit = "%"
		map.name = "humidity"
		log.debug "${device.displayName} humidity is ${map.value}%"
	}
	sendEvent(map)
}

def zwaveEvent(hubitat.zwave.commands.thermostatoperatingstatev1.ThermostatOperatingStateReport cmd) {
	def map = [name: "thermostatOperatingState"]
	switch (cmd.operatingState) {
		case hubitat.zwave.commands.thermostatoperatingstatev1.ThermostatOperatingStateReport.OPERATING_STATE_IDLE:
			map.value = "idle"
            log.debug = "${device.displayName} is ${map.value}"
			break
		case hubitat.zwave.commands.thermostatoperatingstatev1.ThermostatOperatingStateReport.OPERATING_STATE_HEATING:
			map.value = "heating"
            log.debug = "${device.displayName} is ${map.value}"
			break
		case hubitat.zwave.commands.thermostatoperatingstatev1.ThermostatOperatingStateReport.OPERATING_STATE_COOLING:
			map.value = "cooling"
            log.debug = "${device.displayName} is ${map.value}"
			break
		case hubitat.zwave.commands.thermostatoperatingstatev1.ThermostatOperatingStateReport.OPERATING_STATE_FAN_ONLY:
			map.value = "fan only"
            log.debug = "${device.displayName} is ${map.value}"
			break
		case hubitat.zwave.commands.thermostatoperatingstatev1.ThermostatOperatingStateReport.OPERATING_STATE_PENDING_HEAT:
			map.value = "pending heat"
            log.debug = "${device.displayName} is ${map.value}"
			break
		case hubitat.zwave.commands.thermostatoperatingstatev1.ThermostatOperatingStateReport.OPERATING_STATE_PENDING_COOL:
			map.value = "pending cool"
            log.debug = "${device.displayName} is ${map.value}"
			break
		case hubitat.zwave.commands.thermostatoperatingstatev1.ThermostatOperatingStateReport.OPERATING_STATE_VENT_ECONOMIZER:
			map.value = "vent economizer"
            log.debug = "${device.displayName} is ${map.value}"
			break
	}
	// Makes sure we have the correct thermostat mode
	sendHubCommand(new hubitat.device.HubAction(zwave.thermostatModeV2.thermostatModeGet().format()))
	sendEvent(map)
}

def zwaveEvent(hubitat.zwave.commands.thermostatfanstatev1.ThermostatFanStateReport cmd) {
	def map = [name: "thermostatFanState", unit: ""]
	switch (cmd.fanOperatingState) {
		case 0:
			map.value = "idle"
			break
		case 1:
			map.value = "running"
			break
		case 2:
			map.value = "running high"
			break
	}
	sendEvent(map)
}

def zwaveEvent(hubitat.zwave.commands.thermostatmodev2.ThermostatModeReport cmd) {
	def map = [name: "thermostatMode", data:[supportedThermostatModes: state.supportedModes]]
	switch (cmd.mode) {
		case hubitat.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_OFF:
			map.value = "off"
			break
		case hubitat.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_HEAT:
			map.value = "heat"
			break
		case hubitat.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_AUXILIARY_HEAT:
			map.value = "emergency heat"
			break
		case hubitat.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_COOL:
			map.value = "cool"
			break
		case hubitat.zwave.commands.thermostatmodev2.ThermostatModeReport.MODE_AUTO:
			map.value = "auto"
			break
	}
	sendEvent(map)
	updateThermostatSetpoint(null, null)
}

def zwaveEvent(hubitat.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport cmd) {
	def map = [name: "thermostatFanMode", data:[supportedThermostatFanModes: state.supportedFanModes]]
	switch (cmd.fanMode) {
		case hubitat.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport.FAN_MODE_AUTO_LOW:
			map.value = "auto"
			break
		case hubitat.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport.FAN_MODE_LOW:
			map.value = "on"
			break
		case hubitat.zwave.commands.thermostatfanmodev3.ThermostatFanModeReport.FAN_MODE_CIRCULATION:
			map.value = "circulate"
			break
	}
	sendEvent(map)
}

def zwaveEvent(hubitat.zwave.commands.thermostatmodev2.ThermostatModeSupportedReport cmd) {
	def supportedModes = []
	if(cmd.off) { supportedModes << "off" }
	if(cmd.heat) { supportedModes << "heat" }
	if(cmd.cool) { supportedModes << "cool" }
	if(cmd.auto) { supportedModes << "auto" }
	if(cmd.auxiliaryemergencyHeat) { supportedModes << "emergency heat" }

	state.supportedModes = supportedModes
	sendEvent(name: "supportedThermostatModes", value: supportedModes, displayed: false)
}

def zwaveEvent(hubitat.zwave.commands.thermostatfanmodev3.ThermostatFanModeSupportedReport cmd) {
	def supportedFanModes = []
	if(cmd.auto) { supportedFanModes << "auto" }
	if(cmd.circulation) { supportedFanModes << "circulate" }
	if(cmd.low) { supportedFanModes << "on" }

	state.supportedFanModes = supportedFanModes
	sendEvent(name: "supportedThermostatFanModes", value: supportedFanModes, displayed: false)
}

def zwaveEvent(hubitat.zwave.commands.manufacturerspecificv2.ManufacturerSpecificReport cmd) {
	if (cmd.manufacturerName) {
		updateDataValue("manufacturer", cmd.manufacturerName)
	}
	if (cmd.productTypeId) {
		updateDataValue("productTypeId", cmd.productTypeId.toString())
	}
	if (cmd.productId) {
		updateDataValue("productId", cmd.productId.toString())
	}
}

def zwaveEvent(hubitat.zwave.commands.basicv1.BasicReport cmd) {
	log.debug "Zwave BasicReport: $cmd"
}

def zwaveEvent(hubitat.zwave.Command cmd) {
	log.warn "Unexpected zwave command $cmd"
}

// Command Implementations
def poll() {
	// Call refresh which will cap the polling to once every 2 minutes
	log.debug "Polling thermostat for updates"
	refresh()
}

def refresh() {
	// Only allow refresh every 4 minutes to prevent flooding the Zwave network
	def timeNow = now()
	if (!state.refreshTriggeredAt || (4 * 60 * 1000 < (timeNow - state.refreshTriggeredAt))) {
		state.refreshTriggeredAt = timeNow
		if (!state.longRefreshTriggeredAt || (48 * 60 * 60 * 1000 < (timeNow - state.longRefreshTriggeredAt))) {
			state.longRefreshTriggeredAt = timeNow
			// poll supported modes once every 2 days: they're not likely to change
			runIn(10, "longPollDevice", [overwrite: true])
		}
		// use runIn with overwrite to prevent multiple DTH instances run before state.refreshTriggeredAt has been saved
		runIn(2, "pollDevice", [overwrite: true])
	}
}

def pollDevice() {
	def cmds = []
	cmds << new
hubitat.device.HubAction(zwave.thermostatModeV2.thermostatModeGet().format())
	cmds << new hubitat.device.HubAction(zwave.thermostatFanModeV3.thermostatFanModeGet().format())
	cmds << new hubitat.device.HubAction(zwave.sensorMultilevelV2.sensorMultilevelGet().format()) // current temperature
	cmds << new hubitat.device.HubAction(zwave.thermostatOperatingStateV1.thermostatOperatingStateGet().format())
	cmds << new hubitat.device.HubAction(zwave.thermostatSetpointV1.thermostatSetpointGet(setpointType: 1).format())
	cmds << new hubitat.device.HubAction(zwave.thermostatSetpointV1.thermostatSetpointGet(setpointType: 2).format())
	sendHubCommand(cmds)
}

// these values aren't likely to change
def longPollDevice() {
	def cmds = []
	cmds << new hubitat.device.HubAction(zwave.thermostatModeV2.thermostatModeSupportedGet().format())
	cmds << new hubitat.device.HubAction(zwave.thermostatFanModeV3.thermostatFanModeSupportedGet().format())
	sendHubCommand(cmds)
}

def raiseHeatingSetpoint() {
	alterSetpoint(true, "heatingSetpoint")
}

def lowerHeatingSetpoint() {
	alterSetpoint(false, "heatingSetpoint")
}

def raiseCoolSetpoint() {
	alterSetpoint(true, "coolingSetpoint")
}

def lowerCoolSetpoint() {
	alterSetpoint(false, "coolingSetpoint")
}

// Adjusts nextHeatingSetpoint either .5° C/1° F) if raise true/false
def alterSetpoint(raise, setpoint) {
	def locationScale = getTemperatureScale()
	def deviceScale = (state.scale == 1) ? "F" : "C"
	def heatingSetpoint = getTempInLocalScale("heatingSetpoint")
	def coolingSetpoint = getTempInLocalScale("coolingSetpoint")
	def targetValue = (setpoint == "heatingSetpoint") ? heatingSetpoint : coolingSetpoint
	def delta = (locationScale == "F") ? 1 : 0.5
	targetValue += raise ? delta : - delta

	def data = enforceSetpointLimits(setpoint, [targetValue: targetValue, heatingSetpoint: heatingSetpoint, coolingSetpoint: coolingSetpoint])
	// update UI without waiting for the device to respond, this to give user a smoother UI experience
	// also, as runIn's have to overwrite and user can change heating/cooling setpoint separately separate runIn's have to be used
	if (data.targetHeatingSetpoint) {
		sendEvent("name": "heatingSetpoint", "value": getTempInLocalScale(data.targetHeatingSetpoint, deviceScale),
				unit: getTemperatureScale(), eventType: "ENTITY_UPDATE", displayed: false)
	}
	if (data.targetCoolingSetpoint) {
		sendEvent("name": "coolingSetpoint", "value": getTempInLocalScale(data.targetCoolingSetpoint, deviceScale),
				unit: getTemperatureScale(), eventType: "ENTITY_UPDATE", displayed: false)
	}
	if (data.targetHeatingSetpoint && data.targetCoolingSetpoint) {
		runIn(5, "updateHeatingSetpoint", [data: data, overwrite: true])
	} else if (setpoint == "heatingSetpoint" && data.targetHeatingSetpoint) {
		runIn(5, "updateHeatingSetpoint", [data: data, overwrite: true])
	} else if (setpoint == "coolingSetpoint" && data.targetCoolingSetpoint) {
		runIn(5, "updateCoolingSetpoint", [data: data, overwrite: true])
	}
}

def updateHeatingSetpoint(data) {
	updateSetpoints(data)
}

def updateCoolingSetpoint(data) {
	updateSetpoints(data)
}

def enforceSetpointLimits(setpoint, data) {
	def locationScale = getTemperatureScale() 
	def minSetpoint = (setpoint == "heatingSetpoint") ? getTempInDeviceScale(40, "F") : getTempInDeviceScale(50, "F")
	def maxSetpoint = (setpoint == "heatingSetpoint") ? getTempInDeviceScale(90, "F") : getTempInDeviceScale(99, "F")
	def deadband = (state.scale == 1) ? 3 : 2  // 3°F, 2°C
	def targetValue = getTempInDeviceScale(data.targetValue, locationScale)
	def heatingSetpoint = null
	def coolingSetpoint = null
	// Enforce min/mix for setpoints
	if (targetValue > maxSetpoint) {
		targetValue = maxSetpoint
	} else if (targetValue < minSetpoint) {
		targetValue = minSetpoint
	}
	// Enforce 3 degrees F deadband between setpoints
	if (setpoint == "heatingSetpoint") {
		heatingSetpoint = targetValue 
		coolingSetpoint = (heatingSetpoint + deadband > getTempInDeviceScale(data.coolingSetpoint, locationScale)) ? heatingSetpoint + deadband : null
	}
	if (setpoint == "coolingSetpoint") {
		coolingSetpoint = targetValue
		heatingSetpoint = (coolingSetpoint - deadband < getTempInDeviceScale(data.heatingSetpoint, locationScale)) ? coolingSetpoint - deadband : null
	}
	return [targetHeatingSetpoint: heatingSetpoint, targetCoolingSetpoint: coolingSetpoint]
}

def setHeatingSetpoint(degrees) {
	if (degrees) {
		state.heatingSetpoint = degrees.toDouble()
		runIn(2, "updateSetpoints", [overwrite: true])
	}
}

def setCoolingSetpoint(degrees) {
	if (degrees) {
		state.coolingSetpoint = degrees.toDouble()
		runIn(2, "updateSetpoints", [overwrite: true])
	}
}

def updateSetpoints() {
	def deviceScale = (state.scale == 1) ? "F" : "C"
	def data = [targetHeatingSetpoint: null, targetCoolingSetpoint: null]
	def heatingSetpoint = getTempInLocalScale("heatingSetpoint")
	def coolingSetpoint = getTempInLocalScale("coolingSetpoint")
	if (state.heatingSetpoint) {
		data = enforceSetpointLimits("heatingSetpoint", [targetValue: state.heatingSetpoint,
				heatingSetpoint: heatingSetpoint, coolingSetpoint: coolingSetpoint])
	}
	if (state.coolingSetpoint) {
		heatingSetpoint = data.targetHeatingSetpoint ? getTempInLocalScale(data.targetHeatingSetpoint, deviceScale) : heatingSetpoint
		coolingSetpoint = data.targetCoolingSetpoint ? getTempInLocalScale(data.targetCoolingSetpoint, deviceScale) : coolingSetpoint
		data = enforceSetpointLimits("coolingSetpoint", [targetValue: state.coolingSetpoint,
				heatingSetpoint: heatingSetpoint, coolingSetpoint: coolingSetpoint])
		data.targetHeatingSetpoint = data.targetHeatingSetpoint ?: getTempInDeviceScale(heatingSetpoint, getTemperatureScale())
	}
	state.heatingSetpoint = null
	state.coolingSetpoint = null
	updateSetpoints(data)
}

def updateSetpoints(data) {
	def cmds = []
	if (data.targetHeatingSetpoint) {
		cmds << new hubitat.device.HubAction(zwave.thermostatSetpointV1.thermostatSetpointSet(
					setpointType: 1, scale: state.scale, precision: state.precision, scaledValue: data.targetHeatingSetpoint).format())
	}
	if (data.targetCoolingSetpoint) {
		cmds << new hubitat.device.HubAction(zwave.thermostatSetpointV1.thermostatSetpointSet(
					setpointType: 2, scale: state.scale, precision: state.precision, scaledValue: data.targetCoolingSetpoint).format())
	}
	sendHubCommand(cmds)
}

// thermostatSetpoint is not displayed by any tile as it can't be predictable calculated due to
// the device's quirkiness but it is defined by the capability so it must be set, set it to the most likely value
def updateThermostatSetpoint(setpoint, value) {
	def scale = getTemperatureScale()
	def heatingSetpoint = (setpoint == "heatingSetpoint") ? value : getTempInLocalScale("heatingSetpoint")
	def coolingSetpoint = (setpoint == "coolingSetpoint") ? value : getTempInLocalScale("coolingSetpoint")
	def mode = device.currentValue("thermostatMode")
	def thermostatSetpoint = heatingSetpoint    // corresponds to (mode == "heat" || mode == "emergency heat")
	if (mode == "cool") {
		thermostatSetpoint = coolingSetpoint
	} else if (mode == "auto" || mode == "off") {
		// Set thermostatSetpoint to the setpoint closest to the current temperature
		def currentTemperature = getTempInLocalScale("temperature")
		if (currentTemperature > (heatingSetpoint + coolingSetpoint)/2) {
			thermostatSetpoint = coolingSetpoint
		}
	}
	sendEvent(name: "thermostatSetpoint", value: thermostatSetpoint, unit: getTemperatureScale())
}

/**
 * PING is used by Device-Watch in attempt to reach the Device
 * */
def ping() {
	log.debug "ping() called"
	// Just get Operating State there's no need to flood more commands
	sendHubCommand(new hubitat.device.HubAction(zwave.thermostatOperatingStateV1.thermostatOperatingStateGet().format()))
}

def switchMode() {
	def currentMode = device.currentValue("thermostatMode")
	def supportedModes = state.supportedModes
	// Old version of supportedModes was as string, make sure it gets updated
	if (supportedModes && supportedModes.size() && supportedModes[0].size() > 1) {
		def next = { supportedModes[supportedModes.indexOf(it) + 1] ?: supportedModes[0] }
		def nextMode = next(currentMode)
		runIn(2, "setGetThermostatMode", [data: [nextMode: nextMode], overwrite: true])
	} else {
		log.warn "supportedModes not defined"
		getSupportedModes()
	}
}

def switchToMode(nextMode) {
	def supportedModes = state.supportedModes
	// Old version of supportedModes was as string, make sure it gets updated
	if (supportedModes && supportedModes.size() && supportedModes[0].size() > 1) {
		if (supportedModes.contains(nextMode)) {
			runIn(2, "setGetThermostatMode", [data: [nextMode: nextMode], overwrite: true])
		} else {
			log.debug("ThermostatMode $nextMode is not supported by ${device.displayName}")
		}
	} else {
		log.warn "supportedModes not defined"
		getSupportedModes()
	}
}

def getSupportedModes() {
	def cmds = []
	cmds << new hubitat.device.HubAction(zwave.thermostatModeV2.thermostatModeSupportedGet().format())
	sendHubCommand(cmds)
}

def switchFanMode() {
	def currentMode = device.currentValue("thermostatFanMode")
	def supportedFanModes = state.supportedFanModes
	// Old version of supportedFanModes was as string, make sure it gets updated
	if (supportedFanModes && supportedFanModes.size() && supportedFanModes[0].size() > 1) {
		def next = { supportedFanModes[supportedFanModes.indexOf(it) + 1] ?: supportedFanModes[0] }
		def nextMode = next(currentMode)
		runIn(2, "setGetThermostatFanMode", [data: [nextMode: nextMode], overwrite: true])
	} else {
		log.warn "supportedFanModes not defined"
		getSupportedFanModes()
	}
}

def switchToFanMode(nextMode) {
	def supportedFanModes = state.supportedFanModes
	// Old version of supportedFanModes was as string, make sure it gets updated
	if (supportedFanModes && supportedFanModes.size() && supportedFanModes[0].size() > 1) {
		if (supportedFanModes.contains(nextMode)) {
			runIn(2, "setGetThermostatFanMode", [data: [nextMode: nextMode], overwrite: true])
		} else {
			log.debug("FanMode $nextMode is not supported by ${device.displayName}")
		}
	} else {
		log.warn "supportedFanModes not defined"
		getSupportedFanModes()
	}
}

def getSupportedFanModes() {
	def cmds = [new hubitat.device.HubAction(zwave.thermostatFanModeV3.thermostatFanModeSupportedGet().format())]
	sendHubCommand(cmds)
}

def getModeMap() { [
	"off": 0,
	"heat": 1,
	"cool": 2,
	"auto": 3,
	"emergency heat": 4
]}

def setThermostatMode(String value) {
	switchToMode(value)
}

def setGetThermostatMode(data) {
	def cmds = [new hubitat.device.HubAction(zwave.thermostatModeV2.thermostatModeSet(mode: modeMap[data.nextMode]).format()),
			new hubitat.device.HubAction(zwave.thermostatModeV2.thermostatModeGet().format())]
	sendHubCommand(cmds)
}

def getFanModeMap() { [
	"auto": 0,
	"on": 1,
	"circulate": 6
]}

def setThermostatFanMode(String value) {
	switchToFanMode(value)
}

def setGetThermostatFanMode(data) {
	def cmds = [new hubitat.device.HubAction(zwave.thermostatFanModeV3.thermostatFanModeSet(fanMode: fanModeMap[data.nextMode]).format()),
			new hubitat.device.HubAction(zwave.thermostatFanModeV3.thermostatFanModeGet().format())]
	sendHubCommand(cmds)
}

def off() {
	switchToMode("off")
}

def heat() {
	switchToMode("heat")
}

def emergencyHeat() {
	switchToMode("emergency heat")
}

def cool() {
	switchToMode("cool")
}

def auto() {
	switchToMode("auto")
}

def fanOn() {
	switchToFanMode("on")
}

def fanAuto() {
	switchToFanMode("auto")
}

def fanCirculate() {
	switchToFanMode("circulate")
}

// Get stored temperature from currentState in current local scale
def getTempInLocalScale(state) {
	def temp = device.currentState(state)
	if (temp && temp.value && temp.unit) {
		return getTempInLocalScale(temp.value.toBigDecimal(), temp.unit)
	}
	return 0
}

// get/convert temperature to current local scale
def getTempInLocalScale(temp, scale) {
	if (temp && scale) {
		def scaledTemp = convertTemperatureIfNeeded(temp.toBigDecimal(), scale).toDouble()
		return (getTemperatureScale() == "F" ? scaledTemp.round(0).toInteger() : roundC(scaledTemp))
	}
	return 0
}

def getTempInDeviceScale(state) {
	def temp = device.currentState(state)
	if (temp && temp.value && temp.unit) {
		return getTempInDeviceScale(temp.value.toBigDecimal(), temp.unit)
	}
	return 0
}

def getTempInDeviceScale(temp, scale) {
	if (temp && scale) {
		def deviceScale = (state.scale == 1) ? "F" : "C"
		return (deviceScale == scale) ? temp :
				(deviceScale == "F" ? celsiusToFahrenheit(temp).toDouble().round(0).toInteger() : roundC(fahrenheitToCelsius(temp)))
	}
	return 0
}

def roundC (tempC) {
	return (Math.round(tempC.toDouble() * 2))/2
}