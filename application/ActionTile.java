package application;

import java.util.HashSet;

import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;

public class ActionTile { 
	private ActionTileTemplate tileObj;
	private int row, col;
	private double x, y, width, height;
	private HashSet<Player> playerSet;
	
    public ActionTile(HexButton hexButton, int row, int col, double x, double y, double width, double height, String type) {
    	this.row = row;
    	this.col = col;
    	this.x = x;
    	this.y = y;
    	this.width = width;
    	this.height = height;
    	tileObj = createTile(type);
    	if(tileObj == null) {
    		new City();
    	}
    	
    	playerSet = new HashSet<>();
    }
    
    public ActionTileTemplate getTileObj() {return tileObj;}
    
    public boolean takeToken() {
    	TurnHandler turnHandler = TurnHandler.get();
    	Player player = turnHandler.getCurrentPlayer();
    	
    	if(playerSet.size() < 2 && playerSet.add(player)) {
    		tileObj.actionTileObj.removePreviousImages(1);
    		
    		return true;
    	}
    	return false;
    }
    
    public void returnToken() {
    	TurnHandler turnHandler = TurnHandler.get();
    	Player player = turnHandler.getCurrentPlayer();
    	
    	tileObj.replaceTile(2 - playerSet.size());
    	playerSet.remove(player);
    }
    
    private ActionTileTemplate createTile(String type) {
    	ActionTileTemplate obj = null;
    	
    	switch(type) {
    	case "ba":
    		obj = new Barn();
    		
    		break;
    	case "fa":
    		obj = new Farm();
    		
    		break;
    	case "ha":
    		obj = new Harbor();
    		
    		break;
    	case "oa":
    		obj = new Oasis();
    		
    		break;
    	case "or":
    		obj = new Oracle();
    		
    		break;
    	case "pa":
    		obj = new Paddock();
    		
    		break;
    	case "ta":
    		obj = new Tavern();
    		
    		break;
    	case "to":
    		obj = new Tower();
    		
    		break;
    	}
    	return obj;
    }
    
    public class ActionTileTemplate {
    	private String type;
    	private Image locationImage;
    	private Image tileImage;
    	private ActionTileTemplate child;
    	private GameObject actionTileObj;
    	
    	private ActionTileTemplate(String type) {
    		this.type = type;
    		locationImage = new Image(getClass().getResourceAsStream("/images/KB-Location-" + type + ".png"));
    		tileImage = new Image(getClass().getResourceAsStream("/images/KB-" + type + ".png"));
    		actionTileObj = new GameObject();
    		
    		double tileWidth = (width - 31 * (width / 1152)) / 19;
    		double tileHeight = height / 19;
    		
    		actionTileObj.add(locationImage, x + tileWidth * col + col / 10 * (1.5 * (width / 1152)) + row % 2 * (29.5 * (width / 1152)) - 27, y + row * tileHeight - 34.6, 56, 67.2);
    		actionTileObj.add(tileImage, x + tileWidth * col + col / 10 * (1.5 * (width / 1152)) + row % 2 * (29.5 * (width / 1152)) - 27, y + row * tileHeight - 34.6, 56, 67.2);
    		actionTileObj.add(tileImage, x + tileWidth * col + col / 10 * (1.5 * (width / 1152)) + row % 2 * (29.5 * (width / 1152)) - 24, y + row * tileHeight - 31, 50, 60);
    	}
    	
    	public Image getLocationImage() {return locationImage;}
    	
    	private void replaceTile(int x) {
    		double tileWidth = (width - 31 * (width / 1152)) / 19;
    		double tileHeight = height / 19;
    		
    		switch(x) {
    		case 0: 
    			actionTileObj.add(tileImage, x + tileWidth * col + col / 10 * (1.5 * (width / 1152)) + row % 2 * (29.5 * (width / 1152)) - 27 + 383, y + row * tileHeight - 34.6, 56, 67.2);
    			
    			break;
    		case 1:
    			actionTileObj.add(tileImage, x + tileWidth * col + col / 10 * (1.5 * (width / 1152)) + row % 2 * (29.5 * (width / 1152)) - 24 + 383, y + row * tileHeight - 31, 50, 60);
    			
    			break;
    		}
    	}
    	    	
    	private void setChild(ActionTileTemplate child) {
    		this.child = child;
    	}
    	
    	public void setActive(boolean active) {
    		switch(type) {
    		case "ba":
    			if(active) {
    				((Barn)child).activate();
    			} else {
    				((Barn)child).deactivate();
    			}
    		case "fa":
    			if(active) {
    				((Farm)child).activate();
    			} else {
    				((Farm)child).deactivate();
    			}
        		break;
        	case "ha":
        		if(active) {
    				((Harbor)child).activate();
    			} else {
    				((Harbor)child).deactivate();
    			}
        		break;
        	case "oa":
        		if(active) {
    				((Oasis)child).activate();
    			} else {
    				((Oasis)child).deactivate();
    			}
        		break;
        	case "or":
        		if(active) {
    				((Oracle)child).activate();
    			} else {
    				((Oracle)child).deactivate();
    			}
        		break;
        	case "pa":
        		if(active) {
    				((Paddock)child).activate();
    			} else {
    				((Paddock)child).deactivate();
    			}
        		break;
        	case "ta":
        		if(active) {
    				((Tavern)child).activate();
    			} else {
    				((Tavern)child).deactivate();
    			}
        		break;
        	case "to":
        		if(active) {
    				((Tower)child).activate();
    			} else {
    				((Tower)child).deactivate();
    			}
        		break;
    		}
    	}
    }
    
    private class Barn extends ActionTileTemplate {
    	private Barn() {
    		super("barn");
    		super.setChild(this);
    	}
    	
    	private void activate() {
    		
    	}
    	
    	private void deactivate() {
    		
    	}
    }
    
    private class Farm extends ActionTileTemplate {    	
    	private Farm() {
    		super("farm");
    		super.setChild(this);
    	}
    	
    	private void activate() {
    		
    	}
    	
    	private void deactivate() {
    		
    	}
    }
    
    private class Harbor extends ActionTileTemplate {
    	private Harbor() {
    		super("harbor");
    		super.setChild(this);
    	}
    	
    	private void activate() {
    		
    	}
    	
    	private void deactivate() {
    		
    	}
    }
    
    private class Oasis extends ActionTileTemplate {
    	private Oasis() {
    		super("oasis");
    		super.setChild(this);
    	}
    	
    	private void activate() {
    		
    	}
    	
    	private void deactivate() {
    		
    	}
    }
    
    private class Oracle extends ActionTileTemplate {
    	private Oracle() {
    		super("oracle");
    		super.setChild(this);
    	}
    	
    	private void activate() {
    		
    	}
    	
    	private void deactivate() {
    		
    	}
    }
    
    private class Paddock extends ActionTileTemplate {
    	private Paddock() {
    		super("paddock");
    		super.setChild(this);
    	}
    	
    	private void activate() {
    		
    	}
    	
    	private void deactivate() {
    		
    	}
    }
    
    private class Tavern extends ActionTileTemplate {
    	private Tavern() {
    		super("tavern");
    		super.setChild(this);
    	}
    	
    	private void activate() {
    		
    	}
    	
    	private void deactivate() {
    		
    	}
    }
    
    private class Tower extends ActionTileTemplate {
    	private Tower() {
    		super("tower");
    		super.setChild(this);
    	}
    	
    	private void activate() {
    		
    	}
    	
    	private void deactivate() {
    		
    	}
    }
    
    private class City {
    	private Image locationImage;
    	
    	private City() {
    		locationImage = new Image(getClass().getResourceAsStream("/images/KB-Location-City.png"));
    		
    		double tileWidth = (width - 31 * (width / 1152)) / 19;
    		double tileHeight = height / 19;
    		
    		new GameObject(locationImage, x + tileWidth * col + col / 10 * (1.5 * (width / 1152)) + row % 2 * (29.5 * (width / 1152)) - 27, y + row * tileHeight - 34.6, 56, 67.2, 2);
    	}
    }
}









