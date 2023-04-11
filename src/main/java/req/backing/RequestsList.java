/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package req.backing;

import data.RequestRepository;
import jakarta.inject.Named;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;
import req.entities.Request;

/**
 *
 * @author kowal
 */
@Named(value = "requestsList")
@RequestScoped
public class RequestsList {
    
    @Inject
    private RequestRepository requestRepository;
    
   /**
     * Creates a new instance of RequestsList
     */
    public RequestsList() {
    }
    @Size(min=3, max=60, message="{request.size}")
    private String newRequest;

    /**
     * Get the value of newRequest
     *
     * @return the value of newRequest
     */
    public String getNewRequest() {
        return newRequest;
    }

    /**
     * Set the value of newRequest
     *
     * @param newRequest new value of newRequest
     */
    public void setNewRequest(String newRequest) {
        this.newRequest = newRequest;
    }

    private jakarta.faces.component.html.HtmlDataTable requestsDataTable;

    /**
     * Get the value of requestsDataTable
     *
     * @return the value of requestsDataTable
     */
    public jakarta.faces.component.html.HtmlDataTable getRequestsDataTable() {
        return requestsDataTable;
    }

    /**
     * Set the value of requestsDataTable
     *
     * @param requestsDataTable new value of requestsDataTable
     */
    public void setRequestsDataTable(jakarta.faces.component.html.HtmlDataTable requestsDataTable) {
        this.requestsDataTable = requestsDataTable;
    }

    
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }
    
    @Transactional
    public String addRequest()
    {
        Request newReq = new Request();
        newReq.setRequestDate(LocalDate.now());
        newReq.setRequestText(getNewRequest());
        requestRepository.create(newReq);
        setNewRequest("");
        return null;
    }
    
    @Transactional
    public String deleteRequest() {
        Request req =
            (Request) getRequestsDataTable().getRowData();
        requestRepository.remove(req);
        return null;
    }
}
