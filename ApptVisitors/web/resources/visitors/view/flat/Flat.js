Ext.define('Visitors.view.flat.Flat', {
    extend: 'Ext.panel.Panel',
    
    height: 400,
    layout: {
        type: 'border'
    },
    //title: 'Create Flat
    iconCls: 'bookIcon',

    initComponent: function() {
        var me = this;
        var flatStore = Ext.create('Visitors.data.store.FlatStore',{
            url: 'flat/flatList'
        });
        var appartmentStore = Ext.create("Visitors.data.store.AppartmentStore");
        
        
        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'form',
                    id: 'flatForm',
                    bodyPadding: 10,
                    collapsible: true,
                    title: 'Create Flat',
                    titleCollapse: true,
                    region: 'west',
                    split: true,
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
                            xtype: 'combo',
                            fieldLabel: 'Appartment',
                            labelAlign: 'top',
                            id: 'appartmentId',
                            name: 'appartmentId',
                            store: appartmentStore,
                            displayField: 'name',
                            valueField: 'id',
                            triggerAction: 'all',
                            editable: false,
                            listeners:{
                                select:function(){
                                    flatStore.load({
                                            params: {
                                                appartmentId: this.getValue()
                                            }
                                    }); 
                                }
                            }
                        },
                        {
                            xtype: 'textfield',
                            name: 'flatNo',
                            fieldLabel: 'Flat Name',
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
                                        url: 'flat/create',
                                        method:'POST',
                                        waitMsg: 'Processing...',
                                        success:function(form, action){
                                           Ext.Msg.alert('Success',action.result.data);
                                           var appartmentId = Ext.getCmp('appartmentId').getValue();
                                            flatStore.load({
                                                params: {
                                                    appartmentId: appartmentId
                                                }
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
                            handler: function(){
                                var form = this.up('form').getForm();
                                form.reset();
                            }
                        }
                    ]
                },
                {
                    xtype: 'gridpanel',
                    title: 'Flat List',
                    store: flatStore,
                    region: 'center',
                    viewConfig: {

                    },
                    columns: [
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'id',
                            text: 'Id',
                            hidden: true
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'flatNo',
                            text: 'Flat No',
                            flex:1
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'appartmentId',
                            text: 'AppartmentId',
                            hidden: true
                        },
                        {
                            xtype: 'gridcolumn',
                            dataIndex: 'appartmentName',
                            text: 'AppartmentName',
                            hidden: true
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
                                        var appartmentId = Ext.getCmp('appartmentId').getValue();
                                        flatStore.load({
                                            params: {
                                                appartmentId: appartmentId
                                            }
                                        }); 
                                    }
                                }
                            ]
                        }
                    ],
                    listeners:{
                        selectionchange:function(model, records){
                            if(records[0]){
                                Ext.getCmp('flatForm').getForm().loadRecord(records[0]);
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