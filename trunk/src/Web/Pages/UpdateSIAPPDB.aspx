<%@ Page Language="C#" masterpagefile="../../../../_catalogs/masterpage/theme-api.master" title="2 sem título" inherits="Microsoft.SharePoint.WebPartPages.WebPartPage, Microsoft.SharePoint, Version=15.0.0.0, Culture=neutral, PublicKeyToken=71e9bce111e9429c" meta:progid="SharePoint.WebPartPage.Document" %>
<%@ Register tagprefix="SharePoint" namespace="Microsoft.SharePoint.WebControls" assembly="Microsoft.SharePoint, Version=15.0.0.0, Culture=neutral, PublicKeyToken=71e9bce111e9429c" %>
<asp:Content id="Content1" runat="Server" contentplaceholderid="PlaceHolderMain">

<html>
    <head>
<meta name="WebPartPageExpansion" content="full" /></head>
    <body>
        <div class="container" id="angularUploadCtrl" ng-controller='upl_ctrl_producao'>    
            <div class="row content-container">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="card">

                            <div class="card-header">
                                <div class="card-title">
                                    <span class="title">UPLOAD ARQUIVO SIAPP</span>
                                    <span class="description">acesso restrito</span>
                                </div>
                            </div>
                            <div class="card-body">
                                                            
                                <div class="div">                                  

                                    <input id="AttachmentUploadField" value="Selecionar Arquivo" type="file" size="98"  onchange="angular.element($('#angularUploadCtrl')).scope().carrega()"  />
                                    

                                    <input class="btn btn-info" type="button" ng-click="insertSIAPP();" value="Processa" ng-hide="msgErro"/>
                                    <input class="btn btn-info" type="button" ng-click="listaSistemas();" value="Visualiza" />                                   '     
                                    
                                </div>                                                                

                                <table class="datatable table table-striped" cellspacing="0" width="100%" id="tableSistemas" ng-show="todosSistemas">					            				                            
	                                <thead>
		                                <tr>                                                                                        
			                                <th>Carteira</th>
			                                <th>C. Projetos</th>
			                                <th>Sistema</th>
			                                <th>Descrição</th>
		                                </tr>
	                                </thead>
	                                <tbody>                                    
		                                <tr ng-repeat="sistema in todosSistemas">                                                                                        			                                
			                                <td>{{sistema.carteira}}</td>
			                                <td>{{sistema.coordenacao}}</td>
                                            <td>{{sistema.sistema}}</td>
                                            <td>{{sistema.descricao}}</td>			                                
		                                </tr>
	                                </tbody>
                                </table>
                               
                               <div class="div">
                                    <label id="lblResult" class="result"></label>
                                </div>

                                <div id="resultErro" class="alert alert-danger alert-dismissible col-sm-6" role="alert" ng-show="msgErro" >
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <strong>Alerta! </strong>{{msgErro}}
                                </div>

                                <div id="result" class="alert fresh-color alert-success alert-dismissible col-sm-6" role="alert" style="display:none">
                                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <strong>OK! </strong> {{msgSucesso}}
                                </div>
                            
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>        
    </body>
</html>



</asp:Content>
