SurveyCreator.SurveyNestedPropertyEditorItem.dragIconName = "icon-custom-drag";
SurveyCreator.SurveyNestedPropertyEditorItem.deleteIconName = "icon-custom-delete";

Survey.settings.allowShowEmptyTitleInDesignMode = false;

// Color customization
var mainColor = "#0078d4";
var mainHoverColor = "#60C5FB";
var textColor = "#4a4a4a";
var headerColor = "#4a4a4a";
var headerBackgroundColor = "#4a4a4a";
var bodyContainerBackgroundColor = "#f8f8f8";

var defaultThemeColorsSurvey = Survey.StylesManager.ThemeColors["default"];
defaultThemeColorsSurvey["$main-color"] = mainColor;
defaultThemeColorsSurvey["$main-hover-color"] = mainHoverColor;
defaultThemeColorsSurvey["$text-color"] = textColor;
defaultThemeColorsSurvey["$header-color"] = headerColor;
defaultThemeColorsSurvey["$header-background-color"] = headerBackgroundColor;
defaultThemeColorsSurvey["$body-container-background-color"] = bodyContainerBackgroundColor;

var defaultThemeColorsEditor = SurveyCreator.StylesManager.ThemeColors["default"];
defaultThemeColorsEditor["$primary-color"] = mainColor;
defaultThemeColorsEditor["$secondary-color"] = mainColor;
defaultThemeColorsEditor["$primary-hover-color"] = mainHoverColor;
defaultThemeColorsEditor["$primary-text-color"] = textColor;
defaultThemeColorsEditor["$selection-border-color"] = mainColor;

Survey.StylesManager.applyTheme();
SurveyCreator.StylesManager.applyTheme();

var options = {
	showLogicTab : false,
	showJSONEditorTab : false,
	showTestSurveyTab : false,
	questionTypes : [ "radiogroup" ],
	pageEditMode : "single",
	showTitlesInExpressions : true,
	allowEditExpressionsInTextEditor : false,
	showSurveyTitle : "always"
};
// Create the SurveyJS Creator and render it in div with id equals to
// "creatorElement"
var creator = new SurveyCreator.SurveyCreator("creatorElement", options);
// Show toolbox in the right container. It is shown on the left by default
creator.showToolbox = "right";
// Show property grid in the right container, combined with toolbox
creator.showPropertyGrid = "hide";
// Make toolbox active by default
creator.rightContainerActiveItem("toolbox");

// Remove toolbar items except undo/redo buttons
creator.toolbarItems.splice(2, 5);

function sendToServer() {
	if (0 === parseInt($("#userId").val())) {
		alert("Not login");
		window.location = "./survey";
	}
	var url = "/survey/survey/";
	if (0 === parseInt($("#surveyId").val())) {
		url += "new";
	} else {
		url += "update/" + $("#surveyId").val();
	}
	$.ajax({
		url : url,
		type : "POST",
		async : true,
		contentType : "application/json; charset=utf-8",
		data : creator.text,
		dataType : 'json',
		success : function(data) {
		},
		error: function() {
			alert("Survey saved");
			window.location.href = window.location.href.substring(0, window.location.href.lastIndexOf('survey')) + 'home';
		}
	});
}