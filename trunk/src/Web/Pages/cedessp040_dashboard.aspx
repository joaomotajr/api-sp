<%@ Page Language="C#" masterpagefile="../../../../_catalogs/masterpage/theme-api.master" title="2 sem título" inherits="Microsoft.SharePoint.WebPartPages.WebPartPage, Microsoft.SharePoint, Version=15.0.0.0, Culture=neutral, PublicKeyToken=71e9bce111e9429c" meta:progid="SharePoint.WebPartPage.Document" %>
<%@ Register tagprefix="SharePoint" namespace="Microsoft.SharePoint.WebControls" assembly="Microsoft.SharePoint, Version=15.0.0.0, Culture=neutral, PublicKeyToken=71e9bce111e9429c" %>
<asp:Content id="Content1" runat="Server" contentplaceholderid="PlaceHolderMain">
<html> 
  
  <head>
<meta name="WebPartPageExpansion" content="full" />
</head>
<body> 
     
    <div id="angularCtrl" ng-controller='api_base_producao'>
        <div id="includedContent"></div>
    </div>

	<div ng-include src="'http://unidades/sites/CEDESSP/EQUIPES/SitePages/producao/pages/Base.html'"></div>
  </body> 
</html>

</asp:Content>