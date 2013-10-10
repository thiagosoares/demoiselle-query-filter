package br.jus.tre_pa.frameworkdemoiselle.query.filter.bookmarks.view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.gov.frameworkdemoiselle.annotation.NextView;
import br.gov.frameworkdemoiselle.annotation.PreviousView;
import br.gov.frameworkdemoiselle.pagination.Pagination;
import br.gov.frameworkdemoiselle.stereotype.ViewController;
import br.gov.frameworkdemoiselle.template.AbstractListPageBean;
import br.gov.frameworkdemoiselle.transaction.Transactional;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.bookmarks.business.BookmarkBC;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.bookmarks.criteria.BookmarkQueryFilter;
import br.jus.tre_pa.frameworkdemoiselle.query.filter.bookmarks.domain.Bookmark;

@ViewController
@NextView("/bookmark_edit.xhtml")
@PreviousView("/bookmark_list.xhtml")
public class BookmarkListMB extends AbstractListPageBean<Bookmark, Long> {

	private static final long serialVersionUID = 1L;

	@Inject
	private BookmarkBC bc;
	
	@Inject
  private BookmarkQueryFilter filter;
	
	private LazyDataModel<Bookmark> lazyModel;
	
	
	@PostConstruct
    protected void init() {
        lazyModel = new LazyDataModel<Bookmark>() {

            private static final long serialVersionUID = 1L;

            @Override
            public List<Bookmark> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
                Pagination pagination = getPagination();
                pagination.setFirstResult(first);
                pagination.setPageSize(pageSize);

                List<Bookmark> beans = bc.findAll(filter);

                this.setRowCount(pagination.getTotalResults());

                return beans;
            }
        };
    }
	
	@Override
	protected List<Bookmark> handleResultList() {
		return this.bc.findAll();
	}

	@Transactional
	public String deleteSelection() {
		boolean delete;
		for (Iterator<Long> iter = getSelection().keySet().iterator(); iter.hasNext();) {
			Long id = iter.next();
			delete = getSelection().get(id);

			if (delete) {
				bc.delete(id);
				iter.remove();
			}
		}
		return getPreviousView();
	}

	public LazyDataModel<Bookmark> getLazyModel() {
		return lazyModel;
	}

	
	public void setLazyModel(LazyDataModel<Bookmark> lazyModel) {
		this.lazyModel = lazyModel;
	}

	
	
}
