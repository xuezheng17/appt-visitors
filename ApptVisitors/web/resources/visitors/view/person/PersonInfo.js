Ext.define('Visitors.view.person.PersonInfo', {
    extend: 'Ext.form.Panel',
    iconCls: 'userIcon',
    id: 'personInfoForm',
    height: 450,
    padding: 0,
    layout: {
        type: 'border'
    },
    bodyPadding: '',
    title: '',


    createWindow: function(extComponent, title, height, width){
        extComponent.region = 'center';
        var win;
        if(!win){
            win = Ext.create('widget.window',{
                title: title,
                layout: 'border',
                closeAction: 'destroy',
                items:[extComponent],
                autoHeight: true,
                width: width,
                modal: true,
                //minimizable: true,
                //maximizable: true,
                closable:true,
                //collapsible:true,
                //animCollapse: true,
                //maximized: true,
                listeners: {
                    maximize: function(){
                        var viewportHeight = Ext.getCmp('userMainViewport').height;
                        //Ext.Msg.alert('Message',viewportHeight);
                        win.setHeight(viewportHeight);
                        win.doLayout();
                    }
                }
            })
            win.show(this, function(){

            });
        }
    },
    
    setFormVisible: function(visibleStatus){
        Ext.getCmp('personForm').setVisible(visibleStatus);
    },
    
    setUploadImageActionVisible: function(visibleStatus){
        Ext.getCmp('personImageUploadButton').setVisible(visibleStatus);
    },

    setRequestActionVisible: function(visibleStatus){
        Ext.getCmp('meetingRequestButton').setVisible(visibleStatus);
    },


    searchPersons: function(searchKey){
       var personStore = Ext.getStore('personStore');
       personStore.load({
            url: 'user/searchPersons',
            params: {
                searchKey: searchKey
            }
        });
    },

    initComponent: function() {
        var me = this;
        var personStore = Ext.create('Visitors.data.store.PersonStore');
        var personTypeStore = Ext.create('Visitors.data.store.PersonTypeStore');
        var appartmentStore = Ext.create("Visitors.data.store.AppartmentStore");
        var flatStore = Ext.create("Visitors.data.store.FlatStore");

        
        
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'panel',
                    id: 'personForm',
                    margin: '',
                    padding: '',
                    bodyPadding: 10,
                    collapsible: true,
                    title: 'Owner Information',
                    titleCollapse: true,
                    region: 'west',
                    hidden: true,
                    split: true,
                    items: [
                        {
                            xtype: 'fieldset',
                            padding: 5,
                            title: 'Basic Information',
                            items: [
                                {
                                    xtype: 'hidden',
                                    name: 'id',
                                    value: '0'
                                },
                                {
                                    xtype: 'combo',
                                    fieldLabel: 'PersonType',
                                    labelAlign: 'right',
                                    //hiddenName: 'destinationId',
                                    name: 'personType',
                                    store: personTypeStore,
                                    displayField: 'value',
                                    valueField: 'id',
                                    triggerAction: 'all',
                                    editable: false,
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'firstName',
                                    fieldLabel: 'First Name',
                                    labelAlign: 'right',
                                    anchor: '100%',
                                    allowBlank: false
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'middleInitial',
                                    fieldLabel: 'Middle Initial',
                                    labelAlign: 'right',
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'lastName',
                                    fieldLabel: 'Last Name',
                                    labelAlign: 'right',
                                    anchor: '100%'
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            padding: 5,
                            title: 'Position Profile',
                            items: [
                                {
                                    xtype: 'combo',
                                    fieldLabel: 'Appartment',
                                    labelAlign: 'right',
                                    id: 'appartmentCombo',
                                    name: 'appartmentId',
                                    store: appartmentStore,
                                    displayField: 'name',
                                    valueField: 'id',
                                    triggerAction: 'all',
                                    editable: false,
                                    allowBlank: false,
                                    listeners:{
                                        select:function(){
                                           var flatCombo = Ext.getCmp('flatCombo') ;
                                           flatCombo.setDisabled(false);
                                           flatCombo.clearValue();
                                           flatStore.load({
                                                    params: {
                                                        appartmentId: this.getValue()
                                                    }
                                           }); 
                                           bindValue('appartmentCombo', 'appartmentHiddenName');
                                        }
                                    }
                                },
                                {
                                    xtype: 'hidden',
                                    id: 'appartmentHiddenName',
                                    name: 'appartmentHiddenName'
                                },
                                {
                                    xtype: 'combo',
                                    fieldLabel: 'Flat',
                                    labelAlign: 'right',
                                    id: 'flatCombo',
                                    hiddenName: 'flatName',
                                    name: 'flatId',
                                    store: flatStore,
                                    displayField: 'flatNo',
                                    valueField: 'id',
                                    triggerAction: 'all',
                                    editable: false,
                                    disabled: true,
                                    allowBlank: false,
                                    listeners: {
                                        select: function(){
                                           bindValue('flatCombo', 'flatHiddenName'); 
                                        }
                                    }
                                },
                                {
                                    xtype: 'hidden',
                                    id: 'flatHiddenName',
                                    name: 'flatHiddenName'
                                }
                            ]
                        },
                        {
                            xtype: 'fieldset',
                            padding: 5,
                            title: 'Contact Information',
                            items: [
                                {
                                    xtype: 'textfield',
                                    name: 'email',
                                    fieldLabel: 'Email Id',
                                    labelAlign: 'right',
                                    msgTarget: 'under',
                                    anchor: '100%',
                                    vtype: 'email',
                                    allowBlank: false,
                                    invalidText: 'Email cannot be empty.',
                                    listeners: {
                                        blur : function(){
                                            //Ext.Msg.alert('Message','Blur');
                                            isExistingEmail(this.value);
                                        }
                                    }
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'phone1',
                                    fieldLabel: 'Phone1',
                                    labelAlign: 'right',
                                    msgTarget: 'side',
                                    anchor: '100%'
                                },
                                {
                                    xtype: 'textfield',
                                    name: 'phone2',
                                    fieldLabel: 'Phone2',
                                    labelAlign: 'right',
                                    msgTarget: 'side',
                                    anchor: '100%'
                                }
                            ]
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            id: 'personInfoTB',
                            dock: 'bottom',
                            layout: {
                                align: 'middle',
                                pack: 'end',
                                type: 'hbox'
                            },
                            items: [
                                {
                                    xtype: 'button',
                                    id: 'saveButton',
                                    text: 'Save',
                                    formBind: true,
                                    handler: function(){
                                            var form = this.up('form').getForm();
                                            if(form.isValid()){
                                                form.submit({
                                                   enctype: 'multipart/form-data',
                                                   url: 'user/create',
                                                   method:'POST',
                                                   waitMsg: 'Processing...',
                                                   success: function(form, action){
                                                       Ext.Msg.alert('Success',action.result.data);
                                                            personStore.load(function(records, operation, success) {
                                                            console.log('Person created and loaded in list.');
                                                       });                                                       
                                                       
                                                    },
                                                   failure: function(form, action){
                                                       if(action.failureType == Ext.form.Action.CLIENT_INVALID){
                                                           Ext.Msg.alert("Cannot Submit", "Some fields are still invalid! ");
                                                       }
                                                       if(action.failureType == Ext.form.Action.CONNECT_FAILURE){
                                                           Ext.Msg.alert("Failure","Server communication failure: "+
                                                           action.response.status+' '+action.response.statusText);
                                                       }
                                                       if(action.failuretype == Ext.form.Action.SERVER_INVALID){
                                                           Ext.Mst.alert("Warning", "action.result.errormsg");
                                                       }
                                                   }
                                                });
                                            }
                                        }
                                },
                                {
                                    xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    id: 'resetButton',
                                    text: 'Reset',
                                    handler: function(){
                                        this.up('form').getForm().reset();
                                    }
                                }
                            ]
                        }
                    ]
                },
                {
                    xtype: 'panel',
                    //title: 'Person List',
                    region: 'center',
                    autoScroll: true,
                    items: [
                        {
                            xtype: 'gridpanel',
                            title: '',
                            store: personStore,
                            columnLines: true,
                            autoHeight: true,
                            selType: 'rowmodel',
                            autoScroll: true,
                            columns: [
                                {
                                    text: '',
                                    xtype: 'actioncolumn',
                                    width: 30,
                                    align: 'center',
                                    items:[{
                                        id     : 'detailContactPerson',
                                        icon   : '../images/add.gif',
                                        tooltip: 'Request',
                                        handler: function(grid, rowIndex, colIndex){
                                            var component;
                                            var tabContainer = Ext.getCmp('centerTabPanel');
                                            if(Ext.getCmp('visitorsEntryPoint') == null) {
                                                component = Ext.create('Visitors.view.visitors.VisitorsEntry',{
                                                    title: 'Visitors Entry',
                                                    closable: true
                                                });
                                                tabContainer.add(component);
                                                tabContainer.setActiveTab(component);
                                            }
                                            if(Ext.getCmp('visitorsEntryPoint') != null){
                                                component = Ext.getCmp('visitorsEntryPoint');
                                                tabContainer.setActiveTab(component);
                                            }
                                            //var grid = me.up('gridpanel');
                                            var record = grid.getStore().getAt(rowIndex);
                                            
                                            component.setRequesteeInfo(record.get('id'),
                                                                        record.get('firstName')+" "+record.get('middleInitial')+" "+record.get('lastName'),
                                                                        record.get('flatHiddenName'),
                                                                        record.get('appartmentHiddenName'),
                                                                        record.get('phone1'),
                                                                        record.get('onlineStatusId'));
                                            
                                        }
                                    }]
                                },
                                {
                                    xtype: 'templatecolumn',
                                    dataIndex: 'id',
                                    text: 'Image',
                                    width: 65,
                                    hidden:false,
                                    tpl:'<img src="util/personImage/{id}" height="70" width="55" />'
                                    
                                    
                                },
                                {
                                    xtype: 'templatecolumn',
                                    text: 'Name',
                                    dataIndex: 'firstName',
                                    width: 150,
                                    tpl: ''+
                                         '<b>{firstName} {middleInitial} {lastName}</b>'+
                                         '<img src="../images/{onlineStatusId}.jpg" />'+
                                         '<br>{flatHiddenName},'+
                                         '<br>{appartmentHiddenName},'+
                                         '<br>{phone1}'
                                    
                                },
                                /*
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'middleInitial',
                                    text: 'M.Initial'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'lastName',
                                    text: 'lastName'
                                },
                                */
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'appartmentHiddenName',
                                    text: 'Appartment'
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'appartmentId',
                                    text: 'AppartmentId',
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'flatHiddenName',
                                    text: 'Flat No.',
                                    hidden:true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'flatId',
                                    text: 'FlatId',
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'email',
                                    text: 'Email',
                                    width: 150
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'phone1',
                                    text: 'Phone1',
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'personType',
                                    text: 'Person Type',
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'personTypeName',
                                    text: 'Person Type',
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'onlineStatusId',
                                    text: 'Online Status',
                                    hidden: true
                                },
                                {
                                    xtype: 'gridcolumn',
                                    dataIndex: 'password',
                                    text: 'Password',
                                    hidden: false
                                }
                                
                            ],
                            viewConfig: {

                            },
                            listeners: {
                                selectionchange: function(model, records){
                                    if(records[0]){
                                        //Ext.getCmp('personInfo').getForm().loadRecord(records[0]);
                                        this.up('form').getForm().loadRecord(records[0]);
                                    }
                                }
                            }
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                   xtype: 'label',
                                   text: 'Search Key:'
                                },
                                {
                                    xtype: 'textfield',
                                    id: 'personSearchKeyTF',
                                    name: 'searchkey'
                                },
                                {
                                    xtype: 'button',
                                    //text:'Search',
                                    iconCls: 'searchIcon',
                                    handler: function(){
                                        var searchKeyValue = Ext.getCmp('personSearchKeyTF').value;
                                        me.searchPersons(searchKeyValue);
                                    }
                                },
                                {
                                    xtype: 'button',
                                    id: 'meetingRequestButton',
                                    //text: 'Refresh',
                                    iconCls: 'tableRefreshIcon',
                                    handler: function(){
                                            var store = Ext.getStore('personStore');
                                            store.load(function(records, operation, success) {
                                                console.log('Person created and loaded in list.');
                                            });
                                    }
                                },
                                '-',
                                {
                                    xtype: 'tbfill'
                                },
                                {
                                    xtype: 'button',
                                    //text: 'Upload Image',
                                    id: 'personImageUploadButton',
                                    iconCls: 'uploadIcon',
                                    handler: function(){
                                        var grid = me.down('gridpanel');
                                        var arraySelected =grid.getSelectionModel().getSelection();
                                        var record = arraySelected[0];
                                        //Ext.Msg.alert('Message',record.get('id'));
                                        var uploadWindow = Ext.create('Visitors.view.person.UploadPersonImage');
                                        uploadWindow.setPersonId(record.get('id'));
                                        me.createWindow(uploadWindow, 'Upload Image', 80, '40%');
                                    }
                                }
                                
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
});