
/*
 * This methods helps to get the value from Source Component and set 
 * that value to a Target Component.
 * 
 * Here in bindValue method's parameter sourceCmpId is the Source Component's Id and
 * targetCmpId is the Id of Target Component Id.
 * 
 * Note : This method will only work with ExtJS support. 
 */
  function bindValue(sourceCmpId, targetCmpId){
    	var sourceCmp = Ext.getCmp(sourceCmpId);
    	var targetCmp = Ext.getCmp(targetCmpId);
    	
    	var value = sourceCmp.getRawValue();
    	targetCmp.setValue(value);
 }
		
