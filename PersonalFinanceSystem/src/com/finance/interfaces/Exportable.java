package com.finance.interfaces;

/**
 * Interface for objects that can be exported to different formats
 * Condition VI: Interface
 */
public interface Exportable {
    /**
     * Export data to PDF format
     * @param filename Name of the file to export to
     * @return true if export successful, false otherwise
     */
    public boolean exportToPDF(String filename);
    
    /**
     * Export data to Excel format
     * @param filename Name of the file to export to
     * @return true if export successful, false otherwise
     */
    public boolean exportToExcel(String filename);
}
