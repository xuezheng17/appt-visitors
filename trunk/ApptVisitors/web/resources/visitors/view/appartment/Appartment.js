Ext.define('Visitors.view.appartment.Appartment', {
    extend: 'Ext.panel.Panel',
    
    height: 400,
    layout: {
        type: 'border'
    },
    //title: 'Create Designation
    iconCls: 'bookIcon',

    initComponent: function() {
        var me = this;
        var appartmentStore = Ext.create('Visitors.data.store.AppartmentStore',{
            url: 'appartment/appartmentList'
        });
        
        
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    id: 'appartmentForm',
                    bodyPadding: 10,
                    collapsible: true,
                    title: 'Create Appartment',
                    titleCollapse: true,
                    region: 'west',
                    split: true,
                    buttonAlign: 'right',
                    items: [
                        {
                            xtype: 'hidden',
                            name:'id',
                            value: '0'
                        },
                        {
                            xtype: 'hidden',
                            name: 'companyId',
                            value: '0'
                        },
                        {
                            xtype: 'textfield',
                            name: 'name',
                            fieldLabel: 'Appartment Name',
                            labelAlign: 'top',
                            msgTarget: 'side',
                            allowBlank: false,
                            anchor: '100%'
                        },
                        {
                            xtype: 'button',
                            text: 'Save',
                            handler: function(){
                                var form = this.up('form').getForm();
                                if(form.isValid()){
                                    form.submit({
                                        url: 'appartment/create',
                                        method:'POST',
                                        waitMsg: 'Processing...',
                                        success:function(form, action){
                                           Ext.Msg.alert('Success',action.result.data);
                                           //var store = Ext.getStore('designationStore');
                                           appartmentStore.load(function(records, operation, success) {
                                            console.log('Appartment created and loaded in list.');
                                           });   
                                           form.reset();
                                        },
                                        failure:function(form, action){
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
                                    })
                                }
                            }
                        },
                        {
                            xtype: 'button',
                            text: 'Reset',
                            //hidden: true,
                            handler: function(){
                                var form = this.up('form').getForm();
                                form.reset();
                            }
                        }
                    ]
                },
                {
                    xtype: 'gridpanel',
                    title: 'Appartment List',
                    store: appartmentStore,
                    region: 'center',
                    viewConfig: {

                    },
                    columns: [
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'companyId',
                            text: 'Company Id',
                            hidden: true
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'id',
                            text: 'Id',
                            hidden: true
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'name',
                            text: 'Name',
                            flex:1
                        }
                    ],
                    dockedItems: [
                        {
                            xtype: 'toolbar',
                            dock: 'top',
                            items: [
                                {
                                    xtype: 'button',
                                    text: 'Add New',
                                    iconCls: 'addIcon'
                                },
                                {
                                    xtype: 'tbseparator'
                                },
                                {
                                    xtype: 'button',
                                    text: 'Remove',
                                    iconCls: 'removeIcon'
                                },
                                {
                                    xtype: 'tbfill'
                                },
                                {
                                    xtype: 'button',
                                    text: 'Refresh',
                                    iconCls: 'tableRefreshIcon',
                                    handler: function(){
                                        var store = Ext.getStore('appartmentStore');
                                        store.load(function(records, operation, success) {
                                            console.log('Appartment created and loaded in list.');
                                        }); 
                                    }
                                }
                            ]
                        }
                    ],
                    listeners:{
                        selectionchange:function(model, records){
                            if(records[0]){
                                Ext.getCmp('appartmentForm').getForm().loadRecord(records[0]);
                                //this.up('form').getForm().loadRecord(records[0]);
                            }
                            
                        }
                    }
                }
                
            ]
        });

        me.callParent(arguments);
    }
});