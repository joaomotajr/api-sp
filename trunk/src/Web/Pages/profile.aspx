<%@ Page Language="C#" masterpagefile="../../_catalogs/masterpage/theme-api.master" title="1 sem título" inherits="Microsoft.SharePoint.WebPartPages.WebPartPage, Microsoft.SharePoint, Version=15.0.0.0, Culture=neutral, PublicKeyToken=71e9bce111e9429c" meta:progid="SharePoint.WebPartPage.Document" %>
<%@ Register tagprefix="SharePoint" namespace="Microsoft.SharePoint.WebControls" assembly="Microsoft.SharePoint, Version=15.0.0.0, Culture=neutral, PublicKeyToken=71e9bce111e9429c" %>
<asp:Content id="Content1" runat="Server" contentplaceholderid="PlaceHolderMain">

    <style>
        .entry:not(:first-of-type)
        {
            margin-top: 10px;
        }

        .glyphicon
        {
            font-size: 12px;
        }

        .inputProfile {
            text-transform: uppercase;
        }

        .editTimeline {
            cursor:pointer;
        }
		
		.rating{
		  color: #a9a9a9;
		  margin: 0;
		  padding: 0;
		}

		ul.rating {
		  display: inline-block;
		}

		.rating li {
		  list-style-type: none;
		  display: inline-block;
		  padding: 1px;
		  text-align: center;
		  font-weight: bold;
		  cursor: pointer;
		}

		.rating .filled {
		  color: #21568b;
		}	
        
    </style>

    <link rel="stylesheet" type="text/css" href="../StyleLibrary/CSS/AdminLTE.css">
    <script type="text/javascript"  src="../StyleLibrary/DB/JSProfileFunctions.js"></script>

    <div class="container" id="angularCtrl" ng-controller='api_profile_ctrl'>                                    
        <div class="row content-container">           
    
            <div class="col-md-3">

                <!-- Image -->
                <div class="box box-primary">
                <div class="box-body box-profile">                          
                    <img class="profile-user-img img-responsive img-circle" style="margin: 0 auto"  alt="Imagem do Perfil" ng-src="{{currentImage}}" onError="this.src='../StyleLibrary/Images/avatar-tiny.jpg'" />
                    <h3 class="profile-username text-center">{{funcionario.nome}}</h3>
                    <p class="text-muted text-center">{{funcionario.cargo}}</p>                    
	                <a href="#" class="icon fa fa-photo fa-2.0x pull-right" ng-click="addPhoto();" title="Trocar foto" ng-hide="(msgErro || isProfile==false) ? true : false"></a>
                </div>
                </div>

                <!-- About Me Box -->
                <div class="box box-primary">
                    <div class="box-header with-border">
                        <h3 class="box-title">Sobre Mim</h3>
                    </div>

                    <div class="box-body">
                        <strong><i class="fa fa-book margin-r-5"></i>  Formação</strong>
                        <p class="text-muted">
                        <span ng-hide="profileFormacao[2].nome">{{profileFormacao[0].nome}}<br /></span>
                        <span>{{profileFormacao[1].nome}}</span><br />
                        <span>{{profileFormacao[2].nome}}</span>			        
                        </p>

                        <hr>

                        <strong><i class="fa fa-pencil margin-r-5"></i> Conhecimentos</strong>
                        <p>
                        <span class="label label-danger">COBOL</span>
                        <span class="label label-success">CICS</span>
                        <span class="label label-info">DB2</span>
                        <span class="label label-warning">Java</span>
                        <span class="label label-primary">C++</span>
                        </p>
                        <hr>   
                        </span><a href="#" class="icon fa fa-edit fa-2.0x pull-right" ng-click="editProfile();" title="Editar Informações" ng-hide="(msgErro || isProfile==false) ? true : false"></a>
                    </div>
                </div>

                <!-- Info -->
                <div class="box box-primary collapsed-box">
                    <div class="box-header with-border">
                        <strong><i class="fa fa-info margin-r-5"></i> Informações de Cadastro</strong>
                        <div class="box-tools pull-right">
                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus pull-right"></i></button>
                        </div>
                    </div>

                    <div class="box-body">                        
                        <p class="text-muted">{{funcionario.empresa}} <br />
                        {{funcionario.street}}
                        {{funcionario.cidade}} <span ng-show="funcionario.cidade">-{{funcionario.uf}}<br /></span> 
                        {{funcionario.email}}<br />
                        {{funcionario.celular}}<br ng-show="funcionario.celular" />
                        <span ng-if="funcionario.fone != '(999)9999-9999'">{{funcionario.fone}}</span>
                        </p>
                                                
                        <hr ng-show="profileAdmissaoData" />
                        <p ng-show="profileAdmissaoData" class="text-muted">
                        Admissão: {{profileAdmissaoData | date:'dd/MM/yyyy'}}<br />                        
                        <span ng-show="profileHoraEntrada">Horario: {{profileHoraEntrada | date: 'HH:mm' }}</span><span ng-show="profileHoraSaida"> - {{profileHoraSaida | date: 'HH:mm' }}</span>
                        </p>                                                
                    </div>
                </div>

                <!-- Anotações -->
                <div class="box box-primary collapsed-box">
                    <div class="box-header with-border">
                        <strong><i class="fa fa-file-text-o margin-r-5"></i> Anotações</strong>
                        <div class="box-tools pull-right">
                        <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-plus pull-right"></i></button>
                        </div>
                    </div>

                    <div class="box-body">
                        <p>{{profileAnotacoes}}</p>
                    </div>
                </div>
            </div>                        

            <!-- TABS -->
            <div class="col-md-9">
                <div class="nav-tabs-custom">
                <ul class="nav nav-tabs">
                    <li class="active"><a href="#activity" data-toggle="tab">Conhecimentos</a></li>
                    <li><a href="#timeline" data-toggle="tab">Linha do Tempo</a></li>
                    <li><a href="#settings" data-toggle="tab">Detalhes</a></li>
                </ul>                                    

                <div class="tab-content">
                    <!-- Conhecimentos -->
                    <div class="active tab-pane" id="activity">
			            <div class="row">
			            <div class="col-xs-12">
				            <div class="box">
				        
                                <div class="col-xs-6">
                                    <div class="box-header">
					                    <h3 class="box-title">Programação</h3>
				                    </div>

				                    <div class="box-body table-responsive no-padding">                            
                                
					                    <table class="table table-hover">
					                    <tr>
						                    <th width="30%">Nome</th>
						                    <th>Nível de conhecimento</th>
					                    </tr>
										
										<tr ng-repeat="conhecimento in conhecimentos">
											<td>{{conhecimento}}</td>
											<td>
												<div ng-init="rating = star.rating + 1"></div>
												<div class="star-rating" style="font-size: 1.5em;" star-rating rating-value="rating" data-max="5" on-rating-selected="rateFunction(rating, conhecimento)"></div>	
											</td>
										</tr>
										
										<!--
					                    <tr>
						                    <td>RTC</td>
						                    <td>
						                    <div class="group1">
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
						                    </div>
						                    </td>
					                    </tr>
					                    <tr>
						                    <td>Eclipse</td>
						                    <td>
						                    <div class="group2">
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
						                    </div>
						                    </td>
					                    </tr>
					                    <tr>
						                    <td>Visual Studio</td>
						                    <td>
						                    <div class="group3">
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
						                    </div>
						                    </td>
					                    </tr>
					                    <tr>
						                    <td>Clear Case</td>
						                    <td>
						                    <div class="group4">
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
						                    </div>
						                    </td>
					                    </tr>
					                    </table>                                

				                    </div>
                                </div>                              
                                <div class="col-xs-6">
                                    <div class="box-header">
					                    <h3 class="box-title">Banco de Dados</h3>
				                    </div>

				                    <div class="box-body table-responsive no-padding">                            
                                
					                    <table class="table table-hover">
					                    <tr>
						                    <th>Nome</th>
						                    <th>Nível de conhecimento</th>
					                    </tr>
					                    <tr>
						                    <td>RTC</td>
						                    <td>
						                    <div class="group1">
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
						                    </div>
						                    </td>
					                    </tr>
					                    <tr>
						                    <td>Eclipse</td>
						                    <td>
						                    <div class="group2">
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
						                    </div>
						                    </td>
					                    </tr>
					                    <tr>
						                    <td>Visual Studio</td>
						                    <td>
						                    <div class="group3">
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
						                    </div>
						                    </td>
					                    </tr>
					                    <tr>
						                    <td>Clear Case</td>
						                    <td>
						                    <div class="group4">
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
						                    </div>
						                    </td>
					                    </tr>-->
					                    </table>                                

				                    </div>
                                </div>
				            </div>
			            </div>
			            </div>
				  
			            <div class="row">
			            <div class="col-xs-12">
				            <div class="box">
				        
                                <div class="col-xs-6">
                                    <div class="box-header">
					                    <h3 class="box-title">Programação</h3>
				                    </div><!-- /.box-header -->

				                    <div class="box-body table-responsive no-padding">                            
                                
					                    <table class="table table-hover">
					                    <tr>
						                    <th width="30%">Nome</th>
						                    <th>Nível de conhecimento</th>
					                    </tr>
					                    <tr>
						                    <td>RTC</td>
						                    <td>
						                    <div class="group1">
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
						                    </div>
						                    </td>
					                    </tr>
					                    <tr>
						                    <td>Eclipse</td>
						                    <td>
						                    <div class="group2">
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
						                    </div>
						                    </td>
					                    </tr>
					                    <tr>
						                    <td>Visual Studio</td>
						                    <td>
						                    <div class="group3">
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
						                    </div>
						                    </td>
					                    </tr>
					                    <tr>
						                    <td>Clear Case</td>
						                    <td>
						                    <div class="group4">
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
						                    </div>
						                    </td>
					                    </tr>
					                    </table>                                

				                    </div>
                                </div>    
                                                      
                                <div class="col-xs-6">
                                    <div class="box-header">
					                    <h3 class="box-title">Banco de Dados</h3>
				                    </div>

				                    <div class="box-body table-responsive no-padding">                            
                                
					                    <table class="table table-hover">
					                    <tr>
						                    <th width="30%">Nome</th>
						                    <th>Nível de conhecimento</th>
					                    </tr>
					                    <tr>
						                    <td>RTC</td>
						                    <td>
						                    <div class="group1">
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
						                    </div>
						                    </td>
					                    </tr>
					                    <tr>
						                    <td>Eclipse</td>
						                    <td>
						                    <div class="group2">
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
						                    </div>
						                    </td>
					                    </tr>
					                    <tr>
						                    <td>Visual Studio</td>
						                    <td>
						                    <div class="group3">
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
						                    </div>
						                    </td>
					                    </tr>
					                    <tr>
						                    <td>Clear Case</td>
						                    <td>
						                    <div class="group4">
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
							                    <div class="jr-ratenode jr-nomal"></div>
						                    </div>
						                    </td>
					                    </tr>
					                    </table>                                

				                    </div>
                                </div>                              
                             
				            </div>
			            </div>
			            </div>
                    </div>
                    
                    <!-- Timeline -->
                    <div class="tab-pane" id="timeline" title="{{ profile.dataAdmissao ? '' : 'Inicie a Linha do Tempo, preenchendo a data de admissão'}}">                    
                        
                        <div>
                            <div class="radio3 radio-check radio-danger radio-inline">
                                <input type="radio" id="radio4" name="radio2" value="1" ng-model="lookup" title="Prestando trabalhos a Caixa, anterior a admissão">
                                <label for="radio4" title="Prestando trabalhos a Caixa, anterior a admissão">
                                    Anterior Admissão
                                </label>
                            </div>
                            <div class="radio3 radio-check radio-success radio-inline">
                                <input type="radio" id="radio5" name="radio2" value="2" ng-model="lookup">
                                <label for="radio5">
                                    Posterior Admissão
                                </label>
                            </div>
                            
                            <button type="button" ng-click="addTimeline();" class="btn btn-info btn-sm pull-right" 
                                ng-show="profile.dataAdmissao" ng-hide="(msgErro || isProfile==false || !profile.dataAdmissao) ? true : false">Inserir Item</button>
                        </div>

                        <br />
                        
                        <%-- Depois --%>
                        <div ng-show='lookup==2'>
                        
                            <!-- The timeline -->
                            <ul class="timeline timeline-inverse">

                                <%-- Inicio Heaeder --%>
                                <li>
                                    <i class="fa fa-sign-in bg-gray"></i>
                                </li>

                                <li><br /></li>

                                <li ng-show="profileAdmissaoData" class="time-label" >
                                    <span class="bg-green">
                                        {{profileAdmissaoData | date:'dd/MM/yyyy'}}
                                    </span>
                                </li>                                                                                                                      

                                <li>
                                    <i class="fa fa-user-plus bg-green"></i>
                                    <div class="timeline-item">	                                    
	                                    <h3 style="font-style:italic" class="timeline-header no-border">Admissão Caixa</h3>
                                     </div>
                                </li>                                
                                
                                <%-- Repeat Itens --%>
                                <li class="time-label" ng-repeat-start="item in timelinePosAdmissao">
                                    <span class="bg-blue">
                                        {{item.data | date:'dd/MM/yyyy'}}
                                    </span>
                                </li>
                                <li ng-repeat-end>
                                    <i class="fa fa-user bg-blue editTimeline" ng-click="editTimelinePos($index);" title="Editar Item"></i>
                                    <div class="timeline-item">                                        
                                        <h3 class="timeline-header"><strong>{{item.titulo}}</strong>
                                            <span style="font-size:80%;font-weight:normal">
                                                <a href="#" class="icon fa fa-edit pull-right" ng-click="editTimelinePos($index);" title="Editar Item"></a>
                                                <a href="#" class="icon fa fa-times pull-right" ng-click="excluiTimelinePos($index);" title="Excluir Item"></a>
                                            </span>
                                        </h3>
                                        <div class="timeline-body">
                                        {{item.detalhe}}
                                        </div>
                                    </div>
                                </li>                                

                                <%-- Footer --%>
                                <li>
                                    <i class="fa fa-clock-o bg-gray"></i>
                                </li>
                            </ul>
                        </div>

                       <%-- Antes --%>
                        <div ng-show='lookup==1'>                            
                            
                            <ul class="timeline timeline-inverse">
                                <li>
                                    <i class="fa fa-clock-o bg-gray"></i>                                    
                                </li>
                                <li><br /></li>                                                                                                                                                                        

                                <li>
                                    <i class="fa fa-bookmark bg-red"></i>
                                    <div class="timeline-item">	                                    
	                                    <h3 style="font-style:italic" class="timeline-header no-border">Inicio Relacionamento Caixa</h3>
                                     </div>
                                </li>

                                 <%-- Repeat Itens --%>
                                <li class="time-label" ng-repeat-start="item in timelineAntesAdmissao">
                                    <span class="bg-purple">
                                        {{item.data | date:'dd/MM/yyyy'}}
                                    </span>
                                </li>
                                <li ng-repeat-end>                                    
                                    <%--<i class="fa fa-user bg-purple editTimeline" ng-click="editTimelineAntes($index);" title="Editar Item"></i>--%>                  
                                    <div class="timeline-item">
                                        
                                        <h3 class="timeline-header"><strong>{{item.titulo}}</strong>
                                            <span style="font-size:80%;font-weight:normal">
                                                <a href="#" class="icon fa fa-edit pull-right" ng-click="editTimelineAntes($index);" title="Editar Item"></a>
                                                <a href="#" class="icon fa fa-times pull-right" ng-click="excluiTimelineAntes($index);" title="Excluir Item"></a>
                                            </span>
                                        </h3>                                        
                                        <div class="timeline-body">
                                        {{item.detalhe}}
                                        </div>
                                    </div>
                                </li>
                                
                                <%-- Footer --%>
                                <li ng-show="profileAdmissaoData" class="time-label" >
                                    <span class="bg-green">
                                        {{profileAdmissaoData | date:'dd/MM/yyyy'}}
                                    </span>
                                </li>                                                                                                                      
                                <li>
                                    <i class="fa fa-user-plus bg-green"></i>
                                    <div class="timeline-item">	                                    
	                                    <h3 style="font-style:italic" class="timeline-header no-border">Admissão Caixa</h3>
                                    </div>
                                </li>
                                <li>
                                    <i class="fa fa-sign-out bg-gray"></i>
                                </li>
                            </ul>
                        </div>

                    </div>

                    <!-- Detalhes -->
                    <div class="tab-pane" id="settings">

                        <div class="box box-solid">

                            <div class="box-header with-border">
                                <i class="fa fa-text-width"></i> <h3 class="box-title">Informações do Perfil</h3>
                            </div>

                            <div class="box-body" style="font-size:1.2em">
                                <dl class="dl-horizontal">
                                    <dt>Objetivo</dt>
                                        <dd>{{profileObjetivo}}</dd>

                                    <hr />
                                    <dt>Qualificação Profissional</dt>
                                        <dd>{{profileQualificacao}}</dd>
                                    <hr />
                                    <dt>Formação</dt>                                        
                                        <dd ng-repeat="itemFormacao in profileFormacao">
                                            {{itemFormacao.nome}}
                                        </dd>
                                    <hr />
                                    <dt>Experiência</dt>
                                        <dd ng-repeat="itemExperiencia in profileExperiencia">
                                            {{itemExperiencia.nome}}
                                        </dd>
                                    <hr />
                                    <dt>Treinamentos</dt>
                                        <dd ng-repeat="itemTreinamento in profileTreinamento">
                                            {{itemTreinamento.nome}}
                                        </dd>
                                    <hr />
                                    <dt>Certificações</dt>
                                        <dd ng-repeat="itemCertificacao in profileCertificacao">
                                            {{itemCertificacao.nome}}
                                        </dd>
                                </dl>
                            </div>
                        </div>                        
                    </div>
                </div>
                </div>
            </div>
        </div>
        
        <div class="alert fresh-color alert-warning" role="alert" ng-hide="(isProfile==true || msgErro) ? true : false">
	        <strong>ATENÇÃO!</strong> Perfil não Iniciado 
            <a href="#" ng-click="editProfile();" class="alert-link">Clique aqui </a> e Complete as Informações.
        </div>

        <div id="resultErro" class="alert alert-danger alert-dismissible col-sm-6" role="alert" ng-show="msgErro" >
            <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
            <strong>Alerta! </strong>{{msgErro}} 
        </div>        
        
        <div id="result" class="alert fresh-color alert-success col-sm-6" role="alert" style="display:none">
            <strong>OK! </strong> {{msgSucesso}}
        </div>

        <!-- Modal  Photo-->
		<div id="addPhoto" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document">
				
				<!-- Modal content-->
				<div class="modal-content">
				    <div class="modal-header">				                     	                   
				        <h4 class="modal-title">Adicionar Foto ao Perfil</h4>
				    </div>
				                    
				    <div class="modal-body">
				        <div class="panel panel-default">
						    <div class="panel-heading">Selecione uma Foto</div>

                            <div class="form-group">
                                <br />    
                                <input style="padding:15px" name="filePhoto" type="file" id="inputFile" accept=".png, .jpg, .jpeg, .gif"> 
                            </div>
					        
					    </div>
				    </div>                    
				                    
				    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
				        <button id="btnUploadPhoto" type="button" class="btn btn-default" ng-click="uploadPhoto();" data-dismiss="modal" disabled>Processa</button>
				    </div>                    
				                    
				</div>	
			</div>
		</div> <!-- Final da Div Modal -->

        <!-- Modal  Profile-->
		<div id="editProfile" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document">
				
				<!-- Modal content-->
				<div class="modal-content">
				    <div class="modal-header">				                     	                   
				        <h4 class="modal-title">CADASTRO DAS INFORMAÇÕES DO PERFIL</h4>
				    </div>
				                    
				    <div class="modal-body">
                        
				    
                        <div class="box box-solid">
                             <div class="box-body">                                                        
                                 <div class="controls"> 

                                     <%-- OBJETIVO --%>
                                    <div class="form-group">
                                        <h4>Objetivo</h4>
                                        <input type="text" class="form-control inputProfile" placeholder="Enter ..." ng-model='profileObjetivo'">
                                    </div>                       
                                                                          
                                     <div class="box-group" id="accordion">

                                        <%-- COMPLEMENTO --%>
                                        <div class="panel box box-primary">
                                              <div class="box-header with-border">
                                                <h4 class="box-title">
                                                  <a data-toggle="collapse" data-parent="#accordion" href="#collapseZero">
                                                      <i class="fa fa-info margin-r-5"></i>Complemento Informações de Cadastro
                                                  </a>
                                                </h4>
                                              </div>
                                              <div id="collapseZero" class="panel-collapse collapse">                                                                                                                                   
                                                
                                                    <div class="form-group">
                                                        <h5>Data de Admissão:</h5>
                                                        <div class="input-group">
                                                            <div class="input-group-addon">
                                                            <i class="fa fa-calendar"></i>
                                                            </div>
                                                            <input class="form-control" type="date" ng-model="profileAdmissaoData">
                                                        </div>
                                                    </div>                                                                                           
                                                    <div class="form-group">
                                                        <h5>Horário de Trabalho:</h5>
                                            
                                                        <label>Entrada | Saída</label>
                                                        <div class="input-group">
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-clock-o"></i>
                                                            </div>
                                                            <input class="form-control" type="time" ng-model="profileHoraEntrada">                                        
                                                            <div class="input-group-addon">
                                                                <i class="fa fa-clock-o"></i>
                                                            </div>
                                                            <input class="form-control" type="time" ng-model="profileHoraSaida">                                                
                                                        </div>
                                                    </div>                                                         
                                                                                                    
                                              </div>
                                        </div>
                                        
                                        <%-- QUALIFICAÇÃO --%>                                                                                
                                        <div class="panel box box-primary">
                                            <div class="box-header with-border">
                                            <h4 class="box-title">
                                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">                                                  
                                                    <i class="fa fa-black-tie margin-r-5"></i>Qualificação Profissional
                                                </a>
                                            </h4>
                                            </div>
                                            <div id="collapseOne" class="panel-collapse collapse">
                                            <div class="box-body">
                                                <div class="form-group">                                                    
                                                    <form role="form" id="formEscola" autocomplete="off">  
                                                        <textarea class="form-control inputProfile" rows="2" placeholder="Enter ..." ng-model='profileQualificacao'></textarea>
                                                    </form>
                                                </div>
                                            </div>
                                            </div>
                                        </div>
                             
                                        <%-- FORMAÇÃO --%>
                                        <div class="panel box box-primary">
                                            <div class="box-header with-border">
                                            <h4 class="box-title">
                                                <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                                <i class="fa fa-university margin-r-5"></i>Formação
                                                </a>
                                            </h4>
                                            </div>
                                            <div id="collapseTwo" class="panel-collapse collapse">
                                            <div class="box-body">                                                
                                                <form role="form" id="formFormacao" autocomplete="off">                                                    
                                                    <div ng-repeat="itemFormacao in profileFormacao">
                                                        <div class="entry input-group" style="margin-bottom: 5px";>					
                                                            <input type="text" class="form-control formacao" ng-model='itemFormacao.nome'>
                                                            <span class="input-group-addon btn-remove btnFormacaoMenos" ><i class="fa fa-minus-circle"></i></span>                                     						
                                                        </div>                                        
                                                    </div>                                                    
                                                    <div class="entry input-group baseInput">					
                                                        <input type="text" class="form-control formacao inputProfile" placeholder="Digite Instituição de Ensino - Ano/Mês">
                                                        <span class="input-group-addon btn-add btnFormacaoMais"><i class="fa fa-plus-circle"></i></span>                                     						
                                                    </div>                                            
                                                </form>
                                            </div>
                                            </div>
                                        </div>
                                        
                                         <%-- EXPERIENCIA --%>                         
                                        <div class="panel box box-primary">
                                              <div class="box-header with-border">
                                                <h4 class="box-title">
                                                  <a data-toggle="collapse" data-parent="#accordion" href="#collapseThree">
                                                    <i class="fa fa-briefcase margin-r-5"></i>Experiência Profissional
                                                  </a>
                                                </h4>
                                              </div>
                                              <div id="collapseThree" class="panel-collapse collapse">
                                                <div class="box-body">                                                    
                                                    <form role="form" id="formExperiencia" autocomplete="off">                                            
                                                        <div ng-repeat="itemExperiencia in profileExperiencia">
                                                            <div class="entry input-group" style="margin-bottom: 5px";>					
                                                                <textarea class="form-control experiencia" rows="1" ng-model='itemExperiencia.nome'></textarea>
                                                                <span class="input-group-addon btn-remove btnExperienciaMenos" ><i class="fa fa-minus-circle"></i></span>                                     						
                                                            </div>                                        
                                                        </div>

                                                        <div class="entry input-group ">					
                                                            <textarea class="form-control experiencia inputProfile" rows="1" placeholder="Digite Nome Empresa : Ano Inicio - Ano Término"></textarea>
                                                            <span class="input-group-addon btn-add btnExperienciaMais"><i class="fa fa-plus-circle"></i></span>                                     						
                                                        </div>                                            
                                                    </form>
                                                </div>
                                              </div>
                                        </div>                          
                                        
                                         <%-- TREINAMENTO --%>
                                        <div class="panel box box-primary">
                                              <div class="box-header with-border">
                                                <h4 class="box-title">
                                                  <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
                                                    <i class="fa fa-book margin-r-5"></i>Treinamento
                                                  </a>
                                                </h4>
                                              </div>
                                              <div id="collapseFour" class="panel-collapse collapse">
                                                <div class="box-body">                                                    
                                                    <form role="form" id="formTreinamento" autocomplete="off">                                            
                                                        <div ng-repeat="itemTreinamento in profileTreinamento">
                                                            <div class="entry input-group" style="margin-bottom: 5px";>					
                                                                <input type="text" class="form-control treinamento" ng-model='itemTreinamento.nome'">
                                                                <span class="input-group-addon btn-remove btnTreinamentoMenos" ><i class="fa fa-minus-circle"></i></span>                                     						
                                                            </div>                                        
                                                        </div>
                                                        <div class="entry input-group ">					
                                                            <input type="text" class="form-control treinamento inputProfile" placeholder="Digite Treinamento - Ano/Mês">
                                                            <span class="input-group-addon btn-add btnTreinamentoMais"><i class="fa fa-plus-circle"></i></span>                                     						
                                                        </div>                                            
                                                    </form>
                                                </div>
                                              </div>
                                          </div>
                                        
                                        <%-- CERTIFICAÇÃO --%>
                                        <div class="panel box box-primary">
                                              <div class="box-header with-border">
                                                <h4 class="box-title">
                                                  <a data-toggle="collapse" data-parent="#accordion" href="#collapseFive">
                                                    <i class="fa fa-tag margin-r-5"></i>Certificação
                                                  </a>
                                                </h4>
                                              </div>
                                              <div id="collapseFive" class="panel-collapse collapse">
                                                <div class="box-body">                                                    
                                                    <form role="form" id="formCertificacao" autocomplete="off">                                            
                                                        <div ng-repeat="itemCertificacao in profileCertificacao">
                                                            <div class="entry input-group" style="margin-bottom: 5px";>					
                                                                <input type="text" class="form-control certificacao" ng-model='itemCertificacao.nome'">
                                                                <span class="input-group-addon btn-remove btnCertificacaoMenos" ><i class="fa fa-minus-circle"></i></span>                                     						
                                                            </div>                                        
                                                        </div>

                                                        <div class="entry input-group ">					
                                                            <input type="text" class="form-control certificacao inputProfile" placeholder="Digite Certificação - Ano/Mês">
                                                            <span class="input-group-addon btn-add btnCertificacaoMais"><i class="fa fa-plus-circle"></i></span>                                     						
                                                        </div>                                            
                                                    </form>  

                                                </div>
                                              </div>
                                          </div>

                                        <%-- ANOTAÇÕES --%>
                                         <div class="panel box box-primary">
                                              <div class="box-header with-border">
                                                <h4 class="box-title">
                                                  <a data-toggle="collapse" data-parent="#accordion" href="#collapseSix">
                                                    <i class="fa fa-file-text margin-r-5"></i>Anotações
                                                  </a>
                                                </h4>
                                              </div>
                                              <div id="collapseSix" class="panel-collapse collapse">
                                                <div class="box-body">
                                                    <div class="form-group">
                                                        <textarea class="form-control" rows="2" placeholder="Enter ..." ng-model='profileAnotacoes'></textarea>
                                                    </div>

                                                </div>
                                              </div>
                                          </div>

                                     </div>                       	                                                                                               	
                                                       
                                </div>
                            
                            </div>
                         </div>
				    </div>
				                    
				    <div class="modal-footer">                        
                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="loadProfileValues();">Cancelar</button>
                        <button id="btnUpdatePerfil" type="button" class="btn btn-default" ng-click="salvarProfile();" data-dismiss="modal">Salvar</button>                                        				        
				    </div>
				                    
				</div>	
			</div>
		</div> <!-- Final da Div Modal -->

        <!-- Modal  Informacoes-->
		<div id="editInformacoes" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document">
				
				<!-- Modal content-->
				<div class="modal-content">
				    <div class="modal-header">				                     	                   
				        <h4 class="modal-title">Informações</h4>
				    </div>
				                    
				    <div class="modal-body">
				        <div class="panel panel-default">
						    <div class="panel-heading">Complete as Informações de Cadastro</div>

                            <div class="form-group">                                
                                <div class="box-body">                                    
                                    <div class="form-group">
                                        <h4>Data de Admissão:</h4>
                                        <div class="input-group">
                                            <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                            </div>
                                            <input class="form-control" type="date" ng-model="profileAdmissaoData">
                                        </div>
                                    </div>
                                    <hr />                                    
                                    <div class="form-group">
                                        <h4>Horário de Trabalho:</h4>
                                            
                                        <label>Entrada | Saída</label>
                                        <div class="input-group">
                                            <div class="input-group-addon">
                                                <i class="fa fa-clock-o"></i>
                                            </div>
                                            <input class="form-control" type="time" ng-model="profileHoraEntrada">                                        
                                            <div class="input-group-addon">
                                                <i class="fa fa-clock-o"></i>
                                            </div>
                                            <input class="form-control" type="time" ng-model="profileHoraSaida">                                                
                                        </div>
                                    </div>
                                </div>                                
                            </div>					        
					    </div>
				    </div>                    
				                    
				    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="loadProfileValues();">Cancelar</button>
				        <button id="btnGravaInformacoes" type="button" class="btn btn-default" ng-click="salvarProfileInfoAdmissao();" data-dismiss="modal">Salvar</button>
				    </div>                    
				                    
				</div>	
			</div>
		</div> <!-- Final da Div Modal -->

        <!-- Modal  Timeline-->
		<div id="addTimeline" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		    <div class="modal-dialog" role="document">
				
				<!-- Modal content-->
				<div class="modal-content">
				    <div class="modal-header">				                     	                   
				        <h4 class="modal-title">Linha do Tempo</h4>
				    </div>
				                    
				    <div class="modal-body">
				        <div class="panel panel-default">
						    <div class="panel-heading">Inclua Itens na Linha do Tempo</div>

                            <div class="form-group">
                                
                                <div class="box-body">
                                    
                                    <div class="form-group">
                                        <h4>Data:</h4>
                                        <div class="input-group">
                                            <div class="input-group-addon">
                                            <i class="fa fa-calendar"></i>
                                            </div>
                                                                                     
                                            <div class="control">
                                                <input class="form-control" type="date" ng-model="timelineData">
                                            </div>
                                        </div>
                                    </div>                                    
                                    <div class="form-group">
                                        <h4>Título</h4>
                                        <input type="text" class="form-control" placeholder="Digite Cargo e Área" ng-model="timelineTitulo">
                                    </div>
                                    <div class="form-group">
                                        <h4>Detalhes</h4>
                                        <textarea class="form-control" rows="2" placeholder="Digite Detalhes do Item." ng-model="timelineDetalhe"></textarea>                                        
                                    </div>
                                        
                                </div>                                
                            </div>					        
					    </div>
				    </div>                    
				                    
				    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal" ng-click="clearFormTimeline();">Cancelar</button>
				        <button id="btnGravaimeline" type="button" class="btn btn-default" ng-click="salvarItemTimeline();" 
                            data-dismiss="modal" ng-disabled="(timelineData && timelineTitulo && timelineDetalhe) ? false : true">Salvar
				        </button>
				    </div>                    
				                    
				</div>	
			</div>
		</div> <!-- Final da Div Modal -->
                
    </div>    

</asp:Content>
